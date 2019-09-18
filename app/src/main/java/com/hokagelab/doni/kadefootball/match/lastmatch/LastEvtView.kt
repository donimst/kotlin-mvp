package com.hokagelab.doni.kadefootball.match.lastmatch

import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.MatchEventResponse

interface LastEvtView : ApiRepositoryCallback<MatchEventResponse> {
    fun showLoading()
    fun hideLoading()
    fun showFixtureList(data: List<MatchEvent>?)
    fun showEmptyData()
}