package com.marvellist.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marvellist.di.generateFragmentModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

abstract class BaseFragment : Fragment(), KodeinAware {

    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein
        get() = Kodein.lazy {
            extend(parentKodein)
            injectFragmentModule(this)?.let {
                import(it)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutRes = layoutId
        if (layoutRes == 0) {
            throw IllegalArgumentException(
                "getLayoutRes() returned 0, which is not allowed. "
                        + "If you don't want to use getLayoutRes() but implement your own view for this "
                        + "fragment manually, then you have to override onCreateView();")
        } else {
            return inflater.inflate(layoutRes, container, false)
        }
    }

    protected abstract val layoutId: Int

    fun injectFragmentModule(kodein: Kodein.MainBuilder): Kodein.Module = generateFragmentModule(this)

}