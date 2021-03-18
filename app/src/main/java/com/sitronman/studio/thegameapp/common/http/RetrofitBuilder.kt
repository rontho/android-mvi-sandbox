package com.sitronman.studio.thegameapp.common.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun getRetrofit(url: String): Retrofit = Retrofit.Builder()
    .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build())
    .baseUrl(url)
    .addConverterFactory(MoshiConverterFactory.create().asLenient())
    .build()