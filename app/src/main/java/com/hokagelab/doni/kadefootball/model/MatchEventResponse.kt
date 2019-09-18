package com.hokagelab.doni.kadefootball.model

import com.google.gson.annotations.SerializedName

data class MatchEventResponse(@SerializedName("events") val eventList: List<MatchEvent>)