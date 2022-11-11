package com.stefita.astronautsdb.mapper

import com.stefita.domain.common.Mapper
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.domain.entities.AstronautEntity

class AstronautsEntityMapper : Mapper<AstronautEntity, AstronautSource>() {

    override fun mapFrom(data: AstronautEntity): AstronautSource = AstronautSource(
        id = data.id,
        name = data.name
    )
}