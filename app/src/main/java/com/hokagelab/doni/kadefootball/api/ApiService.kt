package com.hokagelab.doni.kadefootball.api

import com.hokagelab.doni.kadefootball.BuildConfig
import com.hokagelab.doni.kadefootball.model.MatchEventResponse
import com.hokagelab.doni.kadefootball.model.PlayersResponse
import com.hokagelab.doni.kadefootball.model.SearchMatchResponse
import com.hokagelab.doni.kadefootball.model.TeamsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/")
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("eventsnextleague.php")
    fun getNextFixture(@Query("id") idLeague: Int): Call<MatchEventResponse>

    @GET("eventspastleague.php")
    fun getLastFixture(@Query("id") idLeague: Int): Call<MatchEventResponse>

    @GET("searchevents.php")
    fun searchFixture(@Query("e") keyword: String, @Query("s") season: Int): Call<SearchMatchResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeams(@Query("id") idLeague: Int): Call<TeamsResponse>

    @GET("lookupteam.php")
    fun getTeams(@Query("id") idTeam: Int): Call<TeamsResponse>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") teamName: String): Call<TeamsResponse>

    @GET("lookup_all_players.php")
    fun getTeamPlayers(@Query("id") idTeam: Int): Call<PlayersResponse>
}