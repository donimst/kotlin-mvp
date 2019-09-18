package com.hokagelab.doni.kadefootball.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(@SerializedName("event") val eventList: List<MatchEvent>)