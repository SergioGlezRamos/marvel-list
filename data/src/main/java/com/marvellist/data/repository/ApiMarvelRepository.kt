package com.marvellist.data.repository

import android.content.Context
import com.marvellist.domain.exception.ErrorHandler
import com.marvellist.domain.exception.NoInternetException
import com.marvellist.domain.manager.NetworkManager
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marvellist.data.BuildConfig
import com.marvellist.data.net.CoroutineCallAdapterFactoryNullSupport
import com.marvellist.data.net.MarvelAPI
import com.marvellist.data.net.SecuredHttpClient
import com.marvellist.data.net.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiMarvelRepository(private val context: Context,
                          private val networkManager: NetworkManager,
                          private val errorHandler: ErrorHandler) : MarvelRepository {

    private lateinit var marvelApi: MarvelAPI

    private var retrofit: Retrofit = createRetrofit()
        set(value) {
            field = value
            marvelApi = field.create(MarvelAPI::class.java)
        }

    init {
        retrofit = createRetrofit()
    }

    override suspend fun getCharacterById(charactersRequest: RequestCharacterModel): ResponseCharacterModel {
        return marvelApi.getCharacterById(charactersRequest.id).await().toDomainModel()
    }


    private fun createNoInternetException(): Interceptor {
        return Interceptor {
            if (!networkManager.isNetworkAvailable())
                throw NoInternetException()

            it.proceed(it.request())
        }
    }

    private fun createHttpClient(): OkHttpClient {

        val arrayInterceptors = mutableListOf<Interceptor>()

        val loggerInterceptor = HttpLoggingInterceptor()

        arrayInterceptors.add(loggerInterceptor)
        arrayInterceptors.add(createNoInternetException())


        return SecuredHttpClient().getOkHttpClient(*arrayInterceptors.toTypedArray())
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_MARVEL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .addCallAdapterFactory(CoroutineCallAdapterFactoryNullSupport.invoke())
            .client(createHttpClient())
            .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .serializeNulls().create()
    }

}