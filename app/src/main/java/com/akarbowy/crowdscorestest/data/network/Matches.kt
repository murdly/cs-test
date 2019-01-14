package com.akarbowy.crowdscorestest.data.network

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable


@JsonSerializable
data class MatchesResponse(
    @Json(name = "included") val included: List<Info>? = null,
    @Json(name = "data") val data: List<Info>? = null
)

@JsonSerializable
data class Info(
    @Json(name = "relationships") val included: Relationship? = null,
    @Json(name = "attributes") val data: Attribute? = null,
    @Json(name = "type") val type: String? = null,  //matches, teams, rounds, state_events, regions, competitions
    @Json(name = "id") val id: String? = null
)

@JsonSerializable
data class Relationship(
    @Json(name = "competition") val competition: RelationshipType? = null,
    @Json(name = "region") val region: RelationshipType? = null,
    @Json(name = "home") val home: RelationshipType? = null,
    @Json(name = "away") val away: RelationshipType? = null,
    @Json(name = "round") val round: RelationshipType? = null,
    @Json(name = "current_state_event") val current_state_event: RelationshipType? = null
)

@JsonSerializable
data class RelationshipType(
    @Json(name = "data") val name: RelationshipData? = null
)

@JsonSerializable
data class RelationshipData(
    @Json(name = "type") val type: String? = null,
    @Json(name = "id") val id: String? = null
)

@JsonSerializable
data class Attribute(
    @Json(name = "name") val name: String? = null,
    @Json(name = "happened_at") val happenedAt: String? = null,
    @Json(name = "state_code") val stateCode: String? = null,
    @Json(name = "flag_name") val flagName: String? = null,
    @Json(name = "score") val score: Score? = null,
    @Json(name = "outcome") val outcome: Score? = null
)

@JsonSerializable
data class Score(
    @Json(name = "home") val home: Int? = null,
    @Json(name = "away") val away: Int? = null
)

@JsonSerializable
data class Outcome(
    @Json(name = "winner") val winner: String? = null //home, away, draw
)