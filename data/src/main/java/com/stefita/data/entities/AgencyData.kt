package com.stefita.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stefita.domain.entities.AgencyEntity

@Entity
data class AgencyData(
    @PrimaryKey
    val id: Int?,
    val abbrev: String?,
    @ColumnInfo(name = "countryCode")
    val country_code: String?,
    val description: String?,
    @ColumnInfo(name = "foundingYear")
    val founding_year: String?,
    @ColumnInfo(name = "imageUrl")
    val image_url: String?,
    @ColumnInfo(name = "logoUrl")
    val logo_url: String?,
    val name: String,
    val type: String?,
    val url: String?
) {
    fun mapDataToEntity(): AgencyEntity = AgencyEntity(
        id = this.id,
        abbrev = this.abbrev,
        countryCode = this.country_code,
        description = this.description,
        foundingYear = this.founding_year,
        logoUrl = this.logo_url,
        name = this.name,
        type = this.type,
        url = this.url,
        imageUrl = this.image_url
    )

    companion object {
        fun mapEntityToData(agencyData: AgencyEntity?) = agencyData?.let {
            AgencyData(
                id = it.id,
                abbrev = it.abbrev,
                country_code = it.countryCode,
                description = it.description,
                founding_year = it.foundingYear,
                logo_url = it.logoUrl,
                name = it.name ?: "",
                type = it.type,
                url = it.url,
                image_url = it.imageUrl
            )
        }
    }
}

