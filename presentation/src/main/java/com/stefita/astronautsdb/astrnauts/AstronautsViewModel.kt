package com.stefita.astronautsdb.astrnauts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stefita.domain.common.Failure
import com.stefita.domain.common.Mapper
import com.stefita.astronautsdb.common.BaseViewModel
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.usecases.GetAstronautsUseCase

class AstronautsViewModel(
    private val getAstronautsUseCase: GetAstronautsUseCase,
    private val mapper: Mapper<AstronautEntity, AstronautSource>
) : BaseViewModel() {

    sealed class ListState {
        object Loading : ListState()
        object Empty : ListState()
        data class Success(
            val astronauts: List<AstronautSource>
        ) : ListState()
    }

    val state = MutableLiveData<ListState>().apply {
        this.value = ListState.Loading
    }

    init {
        loadData()
    }

    private fun loadData() {
        val params = GetAstronautsUseCase.Params(50, 0)
        getAstronautsUseCase(viewModelScope, params) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleFailure(error: Failure) {
        error.exception.message?.let {
            // TODO
        }
    }

    private fun handleSuccess(list: List<AstronautEntity>) {
        when {
            list.isEmpty() -> state.value = ListState.Empty
            else -> {
                val astronautSourceList = mapToPresentation(list)
                state.value = ListState.Success(astronautSourceList)
            }
        }
    }

    private fun mapToPresentation(astronauts: List<AstronautEntity>): List<AstronautSource> {
        return astronauts.map { mapper.mapFrom(it) }
    }
}