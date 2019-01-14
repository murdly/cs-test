package com.akarbowy.crowdscorestest.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CrowdScoresApi {

    @GET("{id}")
    fun getMatches(@Path("id") matchListDate: String): Single<MatchesResponse>

    companion object {
        const val BASE_URL = "http://demo3317540.mockable.io/"

        const val YESTERDAY_MATCH_LIST = "yesterdayMatchList"

        const val TODAY_MATCH_LIST = "todayMatchList"

        const val TOMORROW_MATCH_LIST = "tomorrowMatchList"
    }

}
