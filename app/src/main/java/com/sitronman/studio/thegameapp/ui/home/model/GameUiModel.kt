package com.sitronman.studio.thegameapp.ui.home.model

import com.sitronman.studio.thegameapp.data.model.Game

data class GameUiModel(val name: String, val image: String)

fun Game.toUiModel(): GameUiModel = GameUiModel(this.name, this.image)