package com.stefita.astronautsdb.ui.astronautslist

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
import kotlinx.coroutines.flow.Flow

class AstronautsViewModel(

    private val mapper: Mapper<AstronautEntity, AstronautSource>
) : BaseViewModel() {

    val astronautFlow: Flow<PagingData<AstronautSource>> = Pager(
        config = PagingConfig(pageSize = 50),
        initialKey = null,
        pagingSourceFactory = { PagingAstronautsSource(mapper) }
    ).flow
        .cachedIn(viewModelScope)
}