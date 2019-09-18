package com.hokagelab.doni.kadefootball.player

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.PlayersResponse

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository
) {

    fun getTeamPlayersList(idTeam: Int) {
        apiRepository.getTeamPlayers(idTeam, object : ApiRepositoryCallback<PlayersResponse?> {
            override fun onGetResponse(dataResponse: PlayersResponse?) {
                view.onGetResponse(dataResponse)
            }

            override fun onGetError() {
                view.onGetError()
            }
        })
    }
}