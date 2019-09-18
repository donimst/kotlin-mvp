package com.hokagelab.doni.kadefootball.team

import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.model.TeamsResponse

interface TeamView : ApiRepositoryCallback<TeamsResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>?)
    fun showEmptyData()
}