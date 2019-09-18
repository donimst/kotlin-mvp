package com.hokagelab.doni.kadefootball.model

import com.google.gson.annotations.SerializedName

data class PlayersResponse(@SerializedName("player") val playerList: List<Players>)