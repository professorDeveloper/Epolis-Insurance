package com.azamovhudstc.epolisinsurance.di

import android.content.Context
import com.azamovhudstc.epolisinsurance.data.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .build()


    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://ionline.uz/ru/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    fun getAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

}