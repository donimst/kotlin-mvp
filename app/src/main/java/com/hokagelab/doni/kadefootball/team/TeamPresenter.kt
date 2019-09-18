package com.hokagelab.doni.kadefootball.team

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.TeamsResponse

class TeamPresenter(private val view: TeamView, private val apiRepository: ApiRepository) {

    fun getTeamsList(idLeague: Int) {
        view.showLoading()
        apiRepository.getAllTeams(idLeague, object : ApiRepositoryCallback<TeamsResponse?> {
            override fun onGetResponse(dataResponse: TeamsResponse?) {
                view.onGetResponse(dataResponse)
                view.hideLoading()
            }

            override fun onGetError() {
                view.onGetError()
                view.hideLoading()
            }
        })
    }

    fun searchTeams(teamName: String) {
        view.showLoading()
        apiRepository.searchTeams(teamName, object : ApiRepositoryCallback<TeamsResponse?> {
            override fun onGetResponse(dataResponse: TeamsResponse?) {
                view.onGetResponse(dataResponse)
                view.hideLoading()
            }

            override fun onGetError() {
                view.onGetError()
                view.hideLoading()
            }
        })
    }
}