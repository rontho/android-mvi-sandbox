package com.sitronman.studio.thegameapp.data.model

import com.squareup.moshi.Json

data class Game(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = "",
    @Json(name = "description") val description: String = "",
    @Json(name = "type") val type: String = "",
    @Json(name = "platform") val platform: String = "",
    @Json(name = "image") val image: String = ""
)