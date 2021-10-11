package com.marvellist.di

import android.app.Application
import com.marvellist.data.manager.ContextNetworkManager
import com.marvellist.data.net.MarvelApiErrorHandler
import com.marvellist.domain.exception.ErrorHandler
import com.marvellist.domain.manager.NetworkManager
import com.marvellist.domain.usecase.GetCharacterByIdUseCase
import com.marvellist.domain.usecase.GetCharacterListUseCase
import com.marvellist.domain.usecase.GetElementsByCharacterIdUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

fun generateAppModule(app: Application) = Kodein.Module(name = "AppModule") {

    bind<NetworkManager>() with singleton { ContextNetworkManager(instance()) }

    bind<ErrorHandler>() with singleton { MarvelApiErrorHandler() }

    //USE CASES//

    bind<GetCharacterByIdUseCase>() with provider { GetCharacterByIdUseCase(instance()) }

    bind<GetCharacterListUseCase>() with provider { GetCharacterListUseCase(instance()) }

    bind<GetElementsByCharacterIdUseCase>() with provider { GetElementsByCharacterIdUseCase(instance()) }
}
