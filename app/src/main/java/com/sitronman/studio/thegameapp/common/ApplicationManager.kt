package com.sitronman.studio.thegameapp.common

import com.sitronman.studio.thegameapp.common.injection.appInjectionModule
import org.koin.core.context.GlobalContext

object ApplicationManager {

    fun init() {
        handlerDependencyInjection()
    }

    private fun handlerDependencyInjection() {
        GlobalContext.startKoin {
            modules(appInjectionModule)
        }
    }
}