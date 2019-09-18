package com.hokagelab.doni.kadefootball.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    var id: Long?,
    @SerializedName("idTeam") var teamID: Int,
    @SerializedName("strTeam") var teamName: String?,
    @SerializedName("strTeamBadge") var teamBadge: String?,
    @SerializedName("strTeamFanart1") var teamFanart: String?,
    @SerializedName("intFormedYear") var teamFormedYear: Int?,
    @SerializedName("strManager") var teamManager: String?,
    @SerializedName("strStadium") var teamStadium: String?,
    @SerializedName("strStadiumLocation") var teamLocation: String?,
    @SerializedName("strCountry") var teamCountry: String?,
    @SerializedName("intStadiumCapacity") var teamStdCapacity: Int?,
    @SerializedName("strDescriptionEN") var teamDescription: String?
) : Parcelable