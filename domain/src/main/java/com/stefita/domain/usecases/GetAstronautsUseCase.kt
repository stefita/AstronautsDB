package com.stefita.domain.usecases

import com.stefita.domain.common.Either
import com.stefita.domain.common.Failure
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.repositories.AstronautRepository

class GetAstronautsUseCase(private val repository: AstronautRepository) : BaseUseCase<List<AstronautEntity>, GetAstronautsUseCase.Params>()  {

    override suspend fun run(params: Params): Either<Failure, List<AstronautEntity>?> {
        return try {
            val astronautsList = repository.getAstronauts(
                params.limit,
                params.offset
            )
            Either.Right(astronautsList)
        } catch (exp: Exception) {
            Either.Left(AstronautsListFailure(exp))
        }
    }

    data class Params(val limit: Int, val offset: Int)

    data class AstronautsListFailure(val error: Exception) : Failure.FeatureFailure(error)
}