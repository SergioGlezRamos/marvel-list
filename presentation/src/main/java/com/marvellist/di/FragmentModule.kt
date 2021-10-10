package com.marvellist.di

import androidx.fragment.app.Fragment
import com.marvellist.ui.main.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

fun generateFragmentModule (fragment: Fragment) = Kodein.Module(name = "FragmentModule") {

    //FRAGMENT//

    bind<Fragment>() with provider { fragment }

    //VIEW MODEL//

    bind<MainViewModel>() with provider {
        MainViewModel(
            instance())
    }


}