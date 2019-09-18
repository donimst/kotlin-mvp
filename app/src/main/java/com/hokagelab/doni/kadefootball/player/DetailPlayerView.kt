package com.hokagelab.doni.kadefootball.player

import com.hokagelab.doni.kadefootball.model.Players

interface DetailPlayerView {
    fun initCollapsingBar(player: Players)
    fun showData(player: Players)
}