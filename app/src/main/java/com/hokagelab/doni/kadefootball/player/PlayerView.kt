package com.hokagelab.doni.kadefootball.player

import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.Players
import com.hokagelab.doni.kadefootball.model.PlayersResponse

interface PlayerView : ApiRepositoryCallback<PlayersResponse> {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Players>)
}