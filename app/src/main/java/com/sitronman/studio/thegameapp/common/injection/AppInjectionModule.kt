package com.sitronman.studio.thegameapp.common.injection

import com.sitronman.studio.thegameapp.data.datasource.GameDataSource
import com.sitronman.studio.thegameapp.domain.GameRepository
import com.sitronman.studio.thegameapp.ui.home.model.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appInjectionModule = module {
    single { GameDataSource() }
    single { GameRepository(get()) }
    viewModel { GameViewModel(get()) }
}