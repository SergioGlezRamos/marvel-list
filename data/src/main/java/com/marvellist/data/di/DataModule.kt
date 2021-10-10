package com.marvellist.data.di

import com.marvellist.data.repository.ApiMarvelRepository
import com.marvellist.data.repository.CacheApiMarvelRepository
import com.marvellist.domain.repository.MarvelRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val DOMAIN_MARVEL = "https://gateway.marvel.com:443/"

fun generateDataModule() = Kodein.Module(name = "DataModule") {

    bind<String>() with singleton { DOMAIN_MARVEL }

    bind<MarvelRepository>() with singleton {
        CacheApiMarvelRepository(
            ApiMarvelRepository(instance(), instance(), instance()))
    }
}