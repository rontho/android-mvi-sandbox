package com.sitronman.studio.thegameapp.data.service

import com.sitronman.studio.thegameapp.data.model.Game
import retrofit2.http.GET

interface GameService {
    @GET("gamesApi.json")
    suspend fun getGames(): List<Game>
}