package com.example.autoquest.di

import com.example.autoquest.data.services.QuestsDataServices
import com.example.autoquest.data.services.QuestsTasksServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val OK_HTTP_TIMEOUT = 40L
const val BASE_URL = "https://tkachenkond.github.io/"

val networkModule = module {
    single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
    factory { provideOkHttpClient() }

    single<QuestsDataServices> { createService(get(), BASE_URL, get()) }
    single<QuestsTasksServices> { createService(get(), BASE_URL, get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
    }.build()
}

inline fun <reified T> createService(
    moshi: Moshi,
    apiUrl: String,
    okHttpClient: OkHttpClient
): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}