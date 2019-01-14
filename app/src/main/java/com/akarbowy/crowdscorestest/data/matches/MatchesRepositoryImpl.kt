package com.akarbowy.crowdscorestest.data.matches

import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.CrowdScoresApi
import com.akarbowy.crowdscorestest.data.network.Info
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class MatchesRepositoryImpl @Inject constructor(
    private val api: CrowdScoresApi
) : MatchesRepository {

    override fun loadMatches(day: Day): Single<List<Competition>> {

        val response = when (day) {
            Day.Yesterday -> api.getMatches(CrowdScoresApi.YESTERDAY_MATCH_LIST)
            Day.Today -> api.getMatches(CrowdScoresApi.TODAY_MATCH_LIST)
            Day.Tomorrow -> api.getMatches(CrowdScoresApi.TOMORROW_MATCH_LIST)
        }

        return response.map {
            val rounds = mapRounds(it.data.orEmpty())
            mapCompetitions(it.included.orEmpty(), rounds)
        }
    }

    private fun mapRounds(info: List<Info>): List<Round> {

        val map = mutableMapOf<String, List<Match>>()

        info.forEach {
            val roundId = it.included?.round?.data?.id

            val match =
                Match(
                    Team(id = it.included?.home?.data?.id),
                    Team(id = it.included?.away?.data?.id),
                    it.attribute?.score?.home,
                    it.attribute?.score?.away
                )

            roundId?.let {
                map.put(roundId, map[roundId].orEmpty().plus(match))
            }
        }

        var rounds = emptyList<Round>()
        map.keys.forEach {
            rounds = rounds.plus(Round(id = it, matches = map[it]))
        }

        return rounds
    }

    private fun mapCompetitions(info: List<Info>, listOfRounds: List<Round>): List<Competition> {

        val teams = info.filter { it.type == "teams" }
        val rounds = info.filter { it.type == "rounds" }
        val competitions = info.filter { it.type == "competitions" }

        return listOfRounds.map { round ->

            val roundInfo = rounds.find { it.id == round.id }
            val competitionId = roundInfo?.included?.competition?.data?.id

            round.name = roundInfo?.attribute?.name

            round.matches?.forEach { match ->
                match.home?.name = teams.find { it.id == match.home?.id }?.attribute?.name
                match.away?.name = teams.find { it.id == match.away?.id }?.attribute?.name
            }

            Competition(round = round).apply {
                name = competitions.find { it.id == competitionId }?.attribute?.name
                id = competitionId
            }
        }
    }

}