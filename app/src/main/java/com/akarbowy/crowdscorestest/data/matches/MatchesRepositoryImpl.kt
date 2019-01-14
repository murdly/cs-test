package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class MatchesRepositoryImpl @Inject constructor(
    private val api: CrowdScoresApi
) : MatchesRepository {

    override fun loadMatches(day: Day): Single<List<Competition>> {

        val matches = when (day) {
            Day.Yesterday -> api.getMatches(CrowdScoresApi.YESTERDAY_MATCH_LIST)
            Day.Today -> api.getMatches(CrowdScoresApi.TODAY_MATCH_LIST)
            Day.Tomorrow -> api.getMatches(CrowdScoresApi.TOMORROW_MATCH_LIST)
        }

        val competition = Competition(
            Round(
                listOf(
                    Match(
                        Team("team a", "a"),
                        Team("team b", "b"),
                        2, 0
                    )
                ),
                "test liga",
                "test liga id"
            ), "", "test", "test1"
        )

        val competition2 = Competition(
            Round(
                listOf(
                    Match(
                        Team("team a", "a"),
                        Team("team b", "b"),
                        2, 0
                    ),
                    Match(
                        Team("team aa", "aa"),
                        Team("team bb", "ba"),
                        2, 4
                    )
                ),
                null,
                "test liga id"
            ), "", "test", "test1"
        )

        return matches.map { listOf(competition, competition2, competition2) }
    }

}