package com.marvellist.data.net

import android.annotation.SuppressLint
import com.marvellist.data.BuildConfig
import com.marvellist.data.TIMEOUT_RETROFIT
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

class SecuredHttpClient {

    fun getOkHttpClient(vararg interceptor: Interceptor): OkHttpClient {
        return if (BuildConfig.IGNORE_SECURE_HTTP) {
            getUnsafeOkHttpClient(*interceptor)
        } else {
            getSafeOkHttpClient(*interceptor)
        }
    }

    private fun getUnsafeOkHttpClient(vararg interceptor: Interceptor): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .connectTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
            interceptor.forEach { builder.addInterceptor(it) }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun getSafeOkHttpClient(vararg interceptor: Interceptor): OkHttpClient {
        try {
            val builder = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_RETROFIT, TimeUnit.SECONDS)
            interceptor.forEach { builder.addInterceptor(it) }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}