package com.example.weatherbabic.utils.extensions

import com.example.weatherbabic.utils.EMPTY_SPACE

fun String.capitalizeWords(): String = split(EMPTY_SPACE).map { it.capitalize() }.joinToString(
    EMPTY_SPACE
)