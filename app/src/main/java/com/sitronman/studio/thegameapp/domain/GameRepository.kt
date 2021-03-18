package com.sitronman.studio.thegameapp.domain

import com.sitronman.studio.thegameapp.data.datasource.GameDataSource
import com.sitronman.studio.thegameapp.ui.home.model.GameUiModel
import com.sitronman.studio.thegameapp.ui.home.model.toUiModel

class GameRepository(private val gameDataSource: GameDataSource) {
    suspend fun getGames(): List<GameUiModel> = gameDataSource.getGames().map { it.toUiModel() }
}
