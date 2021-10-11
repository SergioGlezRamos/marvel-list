package com.marvellist.di

import androidx.fragment.app.Fragment
import com.marvellist.ui.character.CharacterViewModel
import com.marvellist.ui.characterdetail.CharacterDetailViewModel
import com.marvellist.ui.about.AboutViewModel
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
            instance()
        )
    }

    bind<CharacterViewModel>() with provider {
        CharacterViewModel(
            instance()
        )
    }

    bind<AboutViewModel>() with provider {
        AboutViewModel(
            instance()
        )
    }

    bind<CharacterDetailViewModel>() with provider {
        CharacterDetailViewModel(
            instance()
        )
    }


}