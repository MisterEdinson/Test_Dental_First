package com.app.dental_first.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val MainBlack = Color(0xFF000000)
val LightBlack = Color(0xFF1A1A1A)
val MainWhite = Color(0xFFFFFFFF)
val SupperLite = Color(0xFFFAFAFA)
val MainSilver = Color(0xFF999999)
val MainGray = Color(0xFF4D4D4D)
val LightGray = Color(0xFFE6E6E6)
val MainBlue = Color(0xFF1634F0)
val MainBlue2 = Color(0xFF007AEB)
val MainBlueLight = Color(0xFFE8EBFE)
val SecondBlue = Color(0xFFE6F2FD)
val MainRed = Color(0xFFFF113C)

val GradientBlue = Brush.linearGradient(
    colorStops = arrayOf(
        0.1f to Color(0xFF7683FF),
        0.9f to Color(0xFF7481F8)
    ),
    start = Offset(0f, 0f),
    end = Offset(0f, Float.POSITIVE_INFINITY)
)