package com.afrosin.popularlib.di.module.network

import android.content.Context
import com.afrosin.popularlib.data.api.GitHubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val apiUrl: String = "https://api.github.com"

    private val gson: Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideGithubApi(context: Context): GitHubApi =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)
}