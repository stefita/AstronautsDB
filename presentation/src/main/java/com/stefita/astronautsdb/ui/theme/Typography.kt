package com.stefita.astronautsdb.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stefita.astronautsdb.presentation.R

private val Exo2 = FontFamily(
    Font(R.font.exo2_thin, FontWeight.Thin),
    Font(R.font.exo2_light, FontWeight.Light),
    Font(R.font.exo2_regular),
    Font(R.font.exo2_semibold, FontWeight.SemiBold),
    Font(R.font.exo2_bold, FontWeight.Bold)
)

val Typography = Typography(
    titleLarge = TextStyle(
        color = Gainsboro,
        fontFamily = Exo2,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        color = PewterBlue50,
        fontFamily = Exo2,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        color = Gainsboro,
        fontFamily = Exo2,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    bodyMedium = TextStyle(
        color = Gainsboro,
        fontFamily = Exo2,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)