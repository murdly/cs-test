package com.akarbowy.crowdscorestest.data.injection

import com.akarbowy.crowdscorestest.data.days.DayProvider
import com.akarbowy.crowdscorestest.data.days.DayProviderImpl
import com.akarbowy.crowdscorestest.data.matches.MatchesRepository
import com.akarbowy.crowdscorestest.data.matches.MatchesRepositoryImpl
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDayProvider(): DayProvider = DayProviderImpl()

    @JvmStatic
    @Provides
    @Singleton
    fun provideMatchesRepository(api: CrowdScoresApi): MatchesRepository = MatchesRepositoryImpl(api)
}