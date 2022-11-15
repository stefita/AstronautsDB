package com.stefita.astronautsdb.mapper

import com.stefita.astronautsdb.entities.AgencySource
import com.stefita.domain.common.Mapper
import com.stefita.domain.entities.AgencyEntity

class AgencyEntityMapper: Mapper<AgencyEntity, AgencySource>() {

    override fun mapFrom(from: AgencyEntity) = AgencySource(
        id = from.id,
        countryCode = from.countryCode,
        description = from.description,
        foundingYear = from.foundingYear,
        logoUrl = from.logoUrl,
        name = from.name,
        type = from.type,
        url = from.url
    )

}