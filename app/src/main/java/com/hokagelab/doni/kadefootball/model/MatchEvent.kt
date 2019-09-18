package com.hokagelab.doni.kadefootball.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchEvent(
    var id: Long?,
    @SerializedName("idEvent") var idEvent: Int,
    @SerializedName("strLeague") var league: String?,
    @SerializedName("intRound") var round: Int?,
    @SerializedName("strDate") var strDate: String?,
    @SerializedName("strTime") var strTime: String?,
    @SerializedName("idHomeTeam") var idHome: Int,
    @SerializedName("idAwayTeam") var idAway: Int,
    @SerializedName("strHomeTeam") var homeTeam: String?,
    @SerializedName("strAwayTeam") var awayTeam: String?,
    @SerializedName("intHomeScore") var homeScore: Int?,
    @SerializedName("intAwayScore") var awayScore: Int?,
    @SerializedName("intHomeShots") var homeShots: Int?,
    @SerializedName("intAwayShots") var awayShots: Int?,
    @SerializedName("strHomeGoalDetails") var homeGoal: String?,
    @SerializedName("strAwayGoalDetails") var awayGoal: String?,
    @SerializedName("strHomeYellowCards") var homeYellow: String?,
    @SerializedName("strAwayYellowCards") var awayYellow: String?,
    @SerializedName("strHomeRedCards") var homeRed: String?,
    @SerializedName("strAwayRedCards") var awayRed: String?,
    @SerializedName("strHomeLineupGoalkeeper") var homeGK: String?,
    @SerializedName("strHomeLineupDefense") var homeDF: String?,
    @SerializedName("strHomeLineupMidfield") var homeMF: String?,
    @SerializedName("strHomeLineupForward") var homeFW: String?,
    @SerializedName("strHomeLineupSubstitutes") var homeSubs: String?,
    @SerializedName("strAwayLineupGoalkeeper") var awayGK: String?,
    @SerializedName("strAwayLineupDefense") var awayDF: String?,
    @SerializedName("strAwayLineupMidfield") var awayMF: String?,
    @SerializedName("strAwayLineupForward") var awayFW: String?,
    @SerializedName("strAwayLineupSubstitutes") var awaySubs: String?
) : Parcelable