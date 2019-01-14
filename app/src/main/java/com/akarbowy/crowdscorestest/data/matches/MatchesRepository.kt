package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import io.reactivex.Single


interface MatchesRepository {

    fun loadMatches(day: Day): Single<List<Competition>>
}