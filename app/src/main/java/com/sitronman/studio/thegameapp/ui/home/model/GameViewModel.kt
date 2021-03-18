package com.sitronman.studio.thegameapp.ui.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sitronman.studio.thegameapp.domain.GameRepository
import com.sitronman.studio.thegameapp.ui.home.intent.MainIntent
import com.sitronman.studio.thegameapp.ui.home.state.HomeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class GameViewModel(private val repository: GameRepository) : ViewModel() {

    val state: StateFlow<HomeState> get() = _state
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<HomeState>(HomeState.Idle)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            _state.value = try {
                HomeState.Games(repository.getGames())
            } catch (e: Exception) {
                HomeState.Error(e.localizedMessage)
            }
        }
    }
}