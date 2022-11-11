package com.stefita.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefita.domain.entities.AstronautEntity

@Entity(tableName = "astronauts")
data class AstronautData(
    val age: Int = 0,
    @Embedded(prefix = "agency_")
    val agency: AgencyData? = null,
    val bio: String = "",
    val date_of_birth: String = "",
    val date_of_death: String? = null,
    val first_flight: String = "",
    val flights_count: Int = 0,
    @PrimaryKey
    val id: Int = 0,
    val instagram: String = "",
    val landings_count: Int = 0,
    val last_flight: String = "",
    val name: String = "",
    val nationality: String = "",
    val profile_image: String = "",
    val profile_image_thumbnail: String = "",
    @Embedded(prefix = "status_")
    val status: StatusData? = null,
    val twitter: String = "",
    @Embedded(prefix = "type_")
    val type: TypeData? = null,
    val url: String = "",
    val wiki: String = ""
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