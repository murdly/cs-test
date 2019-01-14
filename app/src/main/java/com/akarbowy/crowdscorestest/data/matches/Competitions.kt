package com.akarbowy.crowdscorestest.data.matches


data class Competition(
    val round: Round? = null,
    var name: String? = null,
    var id: String? = null
)

data class Round(
    val matches: List<Match>? = null,
    var name: String? = null,
    val id: String? = null
)

data class Match(
    val home: Team? = null,
    val away: Team? = null,
    val homeScore: Int? = null,
    val awayScore: Int? = null
)

data class Team(
    var name: String? = null,
    val id: String? = null
)