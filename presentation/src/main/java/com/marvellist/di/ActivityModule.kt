package com.marvellist.di

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import com.marvellist.base.BaseActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun injectionActivityModule(activity: BaseActivity) = Kodein.Module(name = "ActivityModule") {

    //FRAGMENT//

    bind<FragmentManager>() with provider { activity.supportFragmentManager }

    //ACTIVITY//

    bind<BaseActivity>() with singleton { activity }

    //NAV CONTROLLER//

    bind<NavController>() with singleton { activity.navController }
}
