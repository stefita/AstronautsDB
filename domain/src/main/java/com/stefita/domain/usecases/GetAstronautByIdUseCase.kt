package com.stefita.domain.usecases

import com.stefita.domain.common.Either
import com.stefita.domain.common.Failure
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.repositories.AstronautRepository
import java.lang.Exception

class GetAstronautByIdUseCase(private val repository: AstronautRepository) : BaseUseCase<AstronautEntity, GetAstronautByIdUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, AstronautEntity?> {
        return try {
            val astronaut = repository.getAstronautById(params.id)
            Either.Right(astronaut)
        } catch (exp: Exception) {
            Either.Left(AstronautFailure(exp))
        }
    }

    data class Params(val id: Int)

    data class AstronautFailure(val error: Exception) : Failure.FeatureFailure(error)
}