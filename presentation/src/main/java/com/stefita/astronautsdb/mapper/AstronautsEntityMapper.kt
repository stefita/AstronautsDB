package com.stefita.astronautsdb.mapper

import com.stefita.domain.common.Mapper
import com.stefita.astronautsdb.entities.AstronautsSource
import com.stefita.domain.entities.AstronautEntity

class AstronautsEntityMapper : Mapper<AstronautEntity, AstronautsSource>() {

    override fun mapFrom(data: AstronautEntity): AstronautsSource = AstronautsSource(
        id = data.id,
        name = data.name,
    )
}