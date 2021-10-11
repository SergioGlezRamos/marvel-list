package com.marvellist.data.repository

import android.content.Context
import android.util.Log
import com.marvellist.domain.manager.NetworkManager
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marvellist.data.BuildConfig
import com.marvellist.data.HTTP_ERROR_CODES_START
import com.marvellist.data.PUBLIC_API_KEY
import com.marvellist.data.net.CoroutineCallAdapterFactoryNullSupport
import com.marvellist.data.net.MarvelAPI
import com.marvellist.data.net.SecuredHttpClient
import com.marvellist.data.net.model.*
import com.marvellist.data.utils.getHash
import com.marvellist.domain.exception.*
import com.marvellist.domain.model.RequestCharacterListModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

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
        val ts = Calendar.getInstance().timeInMillis.toString()
        val hash = getHash(ts)
        return marvelApi.getCharacterById(charactersRequest.id,ts, PUBLIC_API_KEY,hash)
            .await()
            .toDomainModel()
    }

    override suspend fun getCharacterList(characterListRequest: RequestCharacterListModel): ResponseCharacterModel {
        val ts = Calendar.getInstance().timeInMillis.toString()
        val hash = getHash(ts)
        return marvelApi.getCharacterList(ts,PUBLIC_API_KEY,hash, characterListRequest.limit, characterListRequest.offset)
            .await()
            .toDomainModel()
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
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY

        arrayInterceptors.add(createRemoteException())
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

    @Throws(Throwable::class)
    private fun createRemoteException(): Interceptor {
        return Interceptor {
            try {
                val response = it.proceed(it.request())
                if (response.code > HTTP_ERROR_CODES_START)
                    throw errorHandler.getException(response.code)!!
                response
            } catch (e: UnknownHostException) {
                e.message?.let { message -> Log.d("Remote Exception",message) }
                throw HostException()
            } catch (e: SocketTimeoutException) {
                e.message?.let { message -> Log.d("Remote Exception",message) }
                throw TimeoutException()
            } catch (e: HttpException) {
                e.message?.let { message -> Log.d("Remote Exception",message) }
                throw errorHandler.getException(e.code())!!
            } catch (e: NotFoundException) {
                e.message?.let { message -> Log.d("Remote Exception",message) }
                throw e
            } catch (e: BaseException) {
                e.message?.let { message -> Log.d("Remote Exception",message) }
                throw e
            }
        }
    }

}