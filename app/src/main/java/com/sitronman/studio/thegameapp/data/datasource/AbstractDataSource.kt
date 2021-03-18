package com.sitronman.studio.thegameapp.data.datasource

import com.sitronman.studio.thegameapp.common.http.getRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class AbstractDataSource<T>(val url: String) {
    inline fun <reified T> getService(): T = getRetrofit(url).create(T::class.java)
}