package com.stefita.astronautsdb.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// /* With # */
//#DDDDDD, #161E29, #123248, #171A1F, #13151B, #46494F, #79A6AF, #FFFFFF, #FFBFA9

val Coral = Color(0xFFf5c2ae)

val PrussianBlue = Color(0xFF0c2231)

val RichBlack = Color(0xFF222530)
val RichBlack29 = Color(0xFF13151B)
val PewterBlue = Color(0x8079A6AF)
val PewterBlue50 = Color(0xFF5c8f99)
val Gainsboro = Color(0xFFdddddd)
val DavysGrey = Color(0xFF46494f)
val RichBlack29Alpha = Color(0x3013151B)

// Rally is always dark themed.
val ColorPalette = darkColorScheme(
    primary = RichBlack29,
    secondary = PrussianBlue,
    surface = RichBlack,
    onSurface = Gainsboro,
    background = RichBlack29,
    onBackground = Gainsboro
)
