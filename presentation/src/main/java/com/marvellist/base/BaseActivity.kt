package com.marvellist.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvellist.di.activityModule
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel>: AppCompatActivity(), KodeinAware {
    protected lateinit var binding: B
    lateinit var viewModel: VM

    private val _parentKodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein, copy = Copy.All)
        bind<Activity>() with singleton { this@BaseActivity }
        bind<Context>("ActivityContext") with singleton { this@BaseActivity }
        import(activityModule)
    }

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
    }

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

    protected abstract fun layoutId(): Int
}