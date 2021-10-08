package com.marvellist.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import com.marvellist.di.injectionActivityModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseActivity: AppCompatActivity(), NavHost, KodeinAware {

    private val parentKodein by closestKodein()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(injectActivityModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onCreateActivity(savedInstanceState)
    }

    protected abstract val layoutId: Int

    abstract fun onCreateActivity(savedInstanceState: Bundle?)

    private fun injectActivityModule(): Kodein.Module = injectionActivityModule(this)
}