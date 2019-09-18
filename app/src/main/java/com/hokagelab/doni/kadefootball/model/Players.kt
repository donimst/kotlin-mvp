package com.hokagelab.doni.kadefootball.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
    @SerializedName("idTeam") var teamID: Int?,
    @SerializedName("idPlayer") var playerID: Int?,
    @SerializedName("strPlayer") var playerName: String?,
    @SerializedName("strPosition") var playerPosition: String?,
    @SerializedName("strCutout") var playerAvatar: String?,
    @SerializedName("strNationality") var playerNationality: String?,
    @SerializedName("dateBorn") var playerBorn: String?,
    @SerializedName("strBirthLocation") var playerBirthLoc: String?,
    @SerializedName("strThumb") var playerBanner: String?,
    @SerializedName("strHeight") var playerHeight: String?,
    @SerializedName("strWeight") var playerWeight: String?,
    @SerializedName("strDescriptionEN") var playerDescription: String?
) : Parcelable