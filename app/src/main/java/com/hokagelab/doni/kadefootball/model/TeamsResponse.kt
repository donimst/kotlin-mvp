package com.hokagelab.doni.kadefootball.model

import com.google.gson.annotations.SerializedName

data class TeamsResponse(@SerializedName("teams") val teamList: List<Teams>)