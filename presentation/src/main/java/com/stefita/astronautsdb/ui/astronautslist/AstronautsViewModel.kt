package com.stefita.astronautsdb.ui.astronautslist

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stefita.astronautsdb.common.BaseViewModel
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.astronautsdb.paging.PagingAstronautsSource
import com.stefita.domain.common.Mapper
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.usecases.GetAstronautsUseCase
import kotlinx.coroutines.flow.Flow

class AstronautsViewModel(
    private val getAstronautsUseCase: GetAstronautsUseCase,
    private val mapper: Mapper<AstronautEntity, AstronautSource>
) : BaseViewModel() {
    val listState: LazyListState = LazyListState()
    val gridState: LazyGridState = LazyGridState()

    val astronautFlow: Flow<PagingData<AstronautSource>> = Pager(
        config = PagingConfig(pageSize = 50),
        initialKey = null,
        pagingSourceFactory = { PagingAstronautsSource(mapper) }
    ).flow
        .cachedIn(viewModelScope)
}