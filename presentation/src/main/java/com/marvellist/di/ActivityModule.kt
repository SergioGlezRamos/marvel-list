package com.marvellist.di

import androidx.lifecycle.ViewModelProvider
import com.marvellist.navigation.ActivityNavigator
import com.marvellist.utils.ViewModelFactory
import com.marvellist.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val MODULE_NAME = "Activity Module"

val activityModule = Kodein.Module(MODULE_NAME, false) {
    bind<Navigator>() with singleton { ActivityNavigator(instance()) }
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(instance("ActivityContext")) }
}