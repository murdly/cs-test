package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import com.akarbowy.crowdscorestest.data.network.MatchesResponse
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class MatchesRepositoryImpl @Inject constructor(
    private val api: CrowdScoresApi
) : MatchesRepository {

    override fun loadMatches(day: Day): Single<MatchesResponse> {

        return when (day) {
            Day.Yesterday -> api.getMatches(CrowdScoresApi.YESTERDAY_MATCH_LIST)
            Day.Today -> api.getMatches(CrowdScoresApi.TODAY_MATCH_LIST)
            Day.Tomorrow -> api.getMatches(CrowdScoresApi.TOMORROW_MATCH_LIST)
        }
    }

}