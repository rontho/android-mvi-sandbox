package com.sitronman.studio.thegameapp.data.datasource

import com.sitronman.studio.thegameapp.data.model.Game
import com.sitronman.studio.thegameapp.data.service.GameService

private const val BASE_URL = "https://rontho.github.io/webview/"

class GameDataSource : AbstractDataSource<GameService>(BASE_URL) {
    suspend fun getGames(): List<Game> = getService<GameService>().getGames()
}