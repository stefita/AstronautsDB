package com.stefita.domain.usecases

import com.stefita.domain.common.Either
import com.stefita.domain.common.Failure

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type?>

    open suspend operator fun invoke(params: Params): Either<Failure, Type?> = run(params)
}