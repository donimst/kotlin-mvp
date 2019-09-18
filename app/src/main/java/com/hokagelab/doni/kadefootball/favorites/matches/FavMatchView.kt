package com.hokagelab.doni.kadefootball.favorites.matches

import com.hokagelab.doni.kadefootball.model.MatchEvent

interface FavMatchView {
    fun showLoading()
    fun hideLoading()
    fun showFavMatchList(data: List<MatchEvent>)
    fun showEmptyData()
}