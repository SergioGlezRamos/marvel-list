package com.marvellist

import com.marvellist.base.BaseApplication
import com.marvellist.data.di.generateDataModule
import com.marvellist.di.generateAppModule
import org.kodein.di.Kodein

class MarvelListApplication :  BaseApplication() {

    override fun injectAppModule(kodein: Kodein.MainBuilder): Kodein.Module = setupAppModules(kodein)

    override val kodein: Kodein
        get() = super.kodein.apply { Kodein }

    private fun setupAppModules(kodein: Kodein.MainBuilder): Kodein.Module {
        kodein.import((generateDataModule()))
        return generateAppModule(this)
    }

    override fun onCreate() {
        super.onCreate()
        //Stetho.initializeWithDefaults(this)
    }
}