package com.stefita.astronautsdb.ui.astronautdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stefita.astronautsdb.common.BaseViewModel
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.domain.common.Failure
import com.stefita.domain.common.Mapper
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.usecases.GetAstronautByIdUseCase
import kotlinx.coroutines.launch

class AstronautDetailsViewModel(
    private val getAstronautByIdUseCase: GetAstronautByIdUseCase,
    private val mapper: Mapper<AstronautEntity, AstronautSource>
) : BaseViewModel() {

    sealed class DetailState {
        object Loading : DetailState()
        object NotFound : DetailState()
        data class Success(
            val astronaut: AstronautSource
        ) : DetailState()
    }

    val state = MutableLiveData<DetailState>()

    suspend fun loadDetail(astronautId: Int) {
        val params = GetAstronautByIdUseCase.Params(astronautId)
        viewModelScope.launch {
            getAstronautByIdUseCase(params).fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleFailure(error: Failure) {
        error.exception.message?.let {
            Log.e("Details went wrong: ", it)
        }
    }

    private fun handleSuccess(astronaut: AstronautEntity?) {
        when (astronaut) {
            null -> DetailState.NotFound
            else -> {
                val astronautSource = mapper.mapFrom(astronaut)
                state.value = DetailState.Success(astronautSource)
            }
        }
    }
}