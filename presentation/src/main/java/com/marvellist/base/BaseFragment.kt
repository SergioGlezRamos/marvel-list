package com.marvellist.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.marvellist.di.generateFragmentModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

abstract class BaseFragment<B : ViewBinding, VM : ViewModel> : Fragment(), KodeinAware {
    protected lateinit var binding: B
    lateinit var viewModel: VM


    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein
        get() = Kodein.lazy {
            extend(parentKodein)
            injectFragmentModule().let {
                import(it)
            }
        }

    abstract fun getViewModelClass(): Class<VM>

    protected abstract fun layoutId(): Int

    private fun injectFragmentModule(): Kodein.Module = generateFragmentModule(this)

}