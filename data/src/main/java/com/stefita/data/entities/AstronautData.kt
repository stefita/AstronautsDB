package com.stefita.data.entities

import com.stefita.domain.entities.AstronautEntity

data class AstronautData(
    var id: Int,
    var name: String,
)

class AstronautDataEntityMapper {

    fun mapAstronautToEntity(data: AstronautData): AstronautEntity = AstronautEntity(
        id = data.id,
        name = data.name,
    )
}

class AstronautEntityDataMapper {

    fun mapAstronautToData(response: AstronautEntity): AstronautData = AstronautData(
        id = response.id,
        name = response.name
    )
}