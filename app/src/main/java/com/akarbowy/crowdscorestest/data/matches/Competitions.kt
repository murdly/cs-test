package com.akarbowy.crowdscorestest.data.matches


data class Competition(
    val round: Round? = null,
    val flag: String? = null,
    val name: String? = null,
    val id: String? = null
)

data class Round(
    val matches: List<Match>? = null,
    val name: String? = null,
    val id: String? = null
)

data class Match(
    val home: Team? = null,
    val away: Team? = null,
    val homeScore: Int? = null,
    val awayScore: Int? = null
)

data class Team(
    val name: String? = null,
    val id: String? = null
)