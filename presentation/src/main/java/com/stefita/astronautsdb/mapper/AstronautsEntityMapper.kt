package com.stefita.astronautsdb.mapper

import com.stefita.domain.common.Mapper
import com.stefita.astronautsdb.entities.AstronautSource
import com.stefita.domain.entities.AstronautEntity

class AstronautsEntityMapper : Mapper<AstronautEntity, AstronautSource>() {

    private val agencyEntityMapper = AgencyEntityMapper()

    override fun mapFrom(data: AstronautEntity): AstronautSource = AstronautSource(
        id = data.id,
        name = data.name,
        dateOfBirth = data.dateOfBirth,
        agency = data.agency?.let {agencyEntityMapper.mapFrom(it) },
        age = data.age,
        dateOfDeath = data.dateOfDeath,
        profileImage = data.profileImage,
        profileImageThumbnail = data.profileImageThumbnail,
        status = data.status,
        type = data.type,
        bio = data.bio,
        wiki = data.wiki,
        firstFlight = data.firstFlight,
        flightsCount = data.flights_count,
        landingsCount = data.landingsCount,
        nationality = data.nationality
    )
}