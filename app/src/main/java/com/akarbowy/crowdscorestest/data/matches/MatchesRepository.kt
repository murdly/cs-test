package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.MatchesResponse
import io.reactivex.Single


interface MatchesRepository {

    fun loadMatches(day: Day): Single<MatchesResponse>
}