package com.akarbowy.crowdscorestest.data.injection

import com.akarbowy.crowdscorestest.data.network.ApplicationJsonAdapterFactory
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @JvmStatic
    @Provides
    @Singleton
    fun provideApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
        converterFactory: Converter.Factory
    ): CrowdScoresApi {

        val client = okHttpClientBuilder.build()
        return builder.client(client)
            .baseUrl(CrowdScoresApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
            .create(CrowdScoresApi::class.java)
    }
}