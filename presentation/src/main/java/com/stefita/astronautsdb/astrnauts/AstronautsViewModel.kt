package com.stefita.astronautsdb.astrnauts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stefita.domain.common.Failure
import com.stefita.domain.common.Mapper
import com.stefita.astronautsdb.common.BaseViewModel
import com.stefita.astronautsdb.entities.AstronautsSource
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.usecases.GetAstronautsUseCase

class AstronautsViewModel(
    private val getAstronautsUseCase: GetAstronautsUseCase,
    private val mapper: Mapper<AstronautEntity, AstronautsSource>
) : BaseViewModel() {

    companion object {
        private const val TAG = "viewModel"
    }

    var savedQuery = ""
    var savedSeason = 0

    sealed class ListState {
        object Loading : ListState()
        object Empty : ListState()
        data class Success(
            val astronauts: List<AstronautsSource>
        ) : ListState()
    }

    val state = MutableLiveData<ListState>().apply {
        this.value = ListState.Loading
    }

    fun loadData() {
        val params = GetAstronautsUseCase.Params(100, 0)
        getAstronautsUseCase.invoke(viewModelScope, params) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleFailure(error: Failure) {
        error.exception.message?.let { Log.d(TAG, it) }
    }

    private fun handleSuccess(list: List<AstronautEntity>) {
        Log.d(TAG, "Success")
        when {
            list.isEmpty() -> state.value = ListState.Empty
            else -> {
                val astronautsSourceList = mapToPresentation(list)
                state.value = ListState.Success(astronautsSourceList)
            }
        }
    }

    private fun mapToPresentation(astronauts: List<AstronautEntity>): List<AstronautsSource> {
        return astronauts.map { mapper.mapFrom(it) }
    }
}