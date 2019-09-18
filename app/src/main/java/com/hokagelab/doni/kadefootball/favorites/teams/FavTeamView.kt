package com.hokagelab.doni.kadefootball.favorites.teams

import com.hokagelab.doni.kadefootball.model.Teams

interface FavTeamView {
    fun showLoading()
    fun hideLoading()
    fun showFavTeamList(data: List<Teams>?)
    fun showEmptyData()
}