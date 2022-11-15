package com.stefita.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefita.domain.entities.AstronautEntity

@Entity(tableName = "astronauts")
data class AstronautData(
    @PrimaryKey
    val id: Int,
    val age: Int,
    @Embedded(prefix = "agency_")
    val agency: AgencyData? = null,
    val bio: String = "",
    @ColumnInfo(name = "dateOfBirth")
    val date_of_birth: String = "",
    @ColumnInfo(name = "dateOfDeath")
    val date_of_death: String = "",
    @ColumnInfo(name = "firstFlight")
    val first_flight: String?,
    @ColumnInfo(name = "flightsCount")
    val flights_count: Int,
    val instagram: String = "",
    @ColumnInfo(name = "landingsCount")
    val landings_count: Int,
    @ColumnInfo(name = "lastFlight")
    val last_flight: String?,
    val name: String = "",
    val nationality: String = "",
    @ColumnInfo(name = "profileImage")
    val profile_image: String = "",
    @ColumnInfo(name = "profileImageThumbnail")
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
        agency = data.agency?.mapDataToEntity(),
        dateOfBirth = data.date_of_birth,
        age = data.age,
        dateOfDeath = data.date_of_death,
        profileImage = data.profile_image,
        profileImageThumbnail = data.profile_image_thumbnail,
        bio = data.bio,
        wiki = data.wiki,
        firstFlight = data.first_flight,
        lastFlight = data.last_flight,
        flights_count = data.flights_count,
        landingsCount = data.landings_count,
        nationality = data.nationality,
        status = data.status?.name,
        type = data.type?.name
    )
}

class AstronautEntityDataMapper {

    fun mapAstronautToData(response: AstronautEntity): AstronautData = AstronautData(
        id = response.id,
        name = response.name,
        agency = AgencyData.mapEntityToData(response.agency),
        date_of_birth = response.dateOfBirth,
        age = response.age,
        date_of_death = response.dateOfDeath ?: "",
        profile_image = response.profileImage,
        profile_image_thumbnail = response.profileImageThumbnail,
        bio = response.bio,
        wiki = response.wiki,
        first_flight = response.firstFlight,
        last_flight = response.lastFlight,
        flights_count = response.flights_count,
        landings_count = response.landingsCount,
        nationality = response.nationality,
        status = response.status?.let { StatusData(name = it) },
        type = response.type?.let { TypeData(name = it) }
    )
}