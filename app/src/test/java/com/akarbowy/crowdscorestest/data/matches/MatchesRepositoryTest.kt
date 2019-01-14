package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import com.akarbowy.crowdscorestest.data.network.MatchesResponse
import com.akarbowy.crowdscorestest.helpers.InstantRxSchedulerRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString


class MatchesRepositoryTest {

    @Rule
    @JvmField
    var instantRxSchedulerRule = InstantRxSchedulerRule()

    private val mockApi: CrowdScoresApi = mock()

    private lateinit var repository: MatchesRepository

    @Before
    fun setUp() {
        repository = MatchesRepositoryImpl(mockApi)

        `when`(mockApi.getMatches(anyString())).thenReturn(Single.just(MatchesResponse()))
    }

    @Test
    fun loadsYesterdaysMatches() {
        repository.loadMatches(Day.Yesterday).subscribe()

        verify(mockApi).getMatches(CrowdScoresApi.YESTERDAY_MATCH_LIST)
    }

    @Test
    fun loadsTodaysMatches() {
        repository.loadMatches(Day.Today).subscribe()

        verify(mockApi).getMatches(CrowdScoresApi.TODAY_MATCH_LIST)
    }

    @Test
    fun loadsTomorrowssMatches() {
        repository.loadMatches(Day.Tomorrow).subscribe()

        verify(mockApi).getMatches(CrowdScoresApi.TOMORROW_MATCH_LIST)
    }

}