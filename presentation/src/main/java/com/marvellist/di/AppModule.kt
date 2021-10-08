package com.marvellist.di

import android.app.Application
import com.marvellist.data.manager.ContextNetworkManager
import com.marvellist.domain.manager.NetworkManager
import com.marvellist.domain.usecase.GetCharacterByIdUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun generateAppModule(app: Application) = Kodein.Module(name = "AppModule") {

    bind<NetworkManager>() with singleton { ContextNetworkManager(instance()) }

    //USE CASES//

    bind<GetCharacterByIdUseCase>() with provider { GetCharacterByIdUseCase(instance()) }
}
