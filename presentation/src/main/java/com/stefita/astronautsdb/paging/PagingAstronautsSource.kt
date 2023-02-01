package com.stefita.astronautsdb.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.domain.common.Either
import com.stefita.domain.common.Failure
import com.stefita.domain.common.Mapper
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.usecases.GetAstronautsUseCase
import org.koin.java.KoinJavaComponent.inject

class PagingAstronautsSource(
    private val mapper: Mapper<AstronautEntity, AstronautSource>
) : PagingSource<Int, AstronautSource>() {

    private val getAstronautsUseCase: GetAstronautsUseCase by inject(GetAstronautsUseCase::class.java)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AstronautSource> {
        val currentPage = params.key ?: 0

        val result = getAstronautsUseCase(
            params = GetAstronautsUseCase.Params(
                limit = params.loadSize,
                offset = currentPage
            )
        )

        // TODO Return LoadStates instead of Either
        return when (result) {
            is Either.Left -> handleFailure(result.a)
            is Either.Right -> {
                handleSuccess(
                    result.b,
                    prevKey = if (currentPage == 0) null else currentPage.minus(1),
                    nextKey = if (result.b?.isEmpty() == true) null else currentPage.plus(1)
                )
            }
        }
    }

    private fun handleFailure(error: Failure): LoadResult<Int, AstronautSource> {
        val errorMessage = error.exception.message ?: ""
        return LoadResult.Error(Throwable(message = errorMessage))
    }

    private fun handleSuccess(
        list: List<AstronautEntity>?,
        prevKey: Int?,
        nextKey: Int?
    ): LoadResult<Int, AstronautSource> {
        val astronautsList = when {
            list.isNullOrEmpty() -> emptyList()
            else -> list.map { mapper.mapFrom(it) }
        }
        return LoadResult.Page(
            data = astronautsList,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, AstronautSource>): Int? = null
}