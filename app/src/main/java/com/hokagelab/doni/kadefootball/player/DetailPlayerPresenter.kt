package com.hokagelab.doni.kadefootball.player

import com.hokagelab.doni.kadefootball.model.Players

class DetailPlayerPresenter(private val view: DetailPlayerView) {

    fun loadPlayerData(data: Players) {
        view.initCollapsingBar(data)
        view.showData(data)
    }
}