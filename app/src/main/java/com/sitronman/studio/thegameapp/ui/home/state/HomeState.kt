package com.sitronman.studio.thegameapp.ui.home.state

import com.sitronman.studio.thegameapp.ui.home.model.GameUiModel

sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Games(val game: List<GameUiModel>) : HomeState()
    data class Error(val error: String?) : HomeState()
}