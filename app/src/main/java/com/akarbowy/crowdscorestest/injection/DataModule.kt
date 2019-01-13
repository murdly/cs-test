package com.akarbowy.crowdscorestest.injection

import com.akarbowy.crowdscorestest.data.MatchesRepository
import com.akarbowy.crowdscorestest.data.MatchesRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder().build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideMatchesRepository(): MatchesRepository = MatchesRepositoryImpl()
}