package com.azamovhudstc.epolisinsurance.di

import android.content.Context
import com.azamovhudstc.epolisinsurance.data.remote.api.AuthApi
import com.azamovhudstc.epolisinsurance.data.remote.api.BuyPollsApi
import com.azamovhudstc.epolisinsurance.data.remote.api.GrossUzApi
import com.azamovhudstc.epolisinsurance.utils.converter.CustomConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .build()


    @[Provides Singleton Named("ionlineApi")]
    fun getIonlineRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://ionline.uz/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton Named("grossuzApi")]
    fun getGrossUzRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://gross.uz//")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    fun getAuthApi(@Named("ionlineApi") retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)
    @Provides
    fun getBuyPolisAPI(@Named("ionlineApi") retrofit: Retrofit): BuyPollsApi =
        retrofit.create(BuyPollsApi::class.java)

    @Provides
    fun getTechPassApi(@Named("grossuzApi") retrofit: Retrofit): GrossUzApi =
        retrofit.create(GrossUzApi::class.java)

}