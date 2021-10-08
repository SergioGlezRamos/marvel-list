package com.marvellist.base

import android.app.Application
import com.marvellist.data.di.generateDataModule
import com.marvellist.di.generateAppModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

abstract class BaseApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein {
        injectAppModule(this)?.let {
            import(it)
        }?: androidXModule(this@BaseApplication)
    }

    private fun setupAppModules(kodein: Kodein.MainBuilder): Kodein.Module {
        kodein.import((generateDataModule()))
        return generateAppModule(this)
    }

    /**
     * The child classes implement this methods to return the module that provides the app scope objects
     * @param kodein The kodein object which provide the injection
     * @return The Kodein module which makes the injection
     */
    abstract fun injectAppModule(kodein:Kodein.MainBuilder):Kodein.Module?
}