package com.hokagelab.doni.kadefootball.favorites.teams

import com.hokagelab.doni.kadefootball.db.FavoriteRepository

class FavTeamPresenter(private val view: FavTeamView, private val favoriteRepository: FavoriteRepository) {

    fun getFavoriteTeamList() {
        view.showLoading()
        val favList = favoriteRepository.getFavTeam()
        view.hideLoading()
        when (favList.isEmpty()) {
            true -> view.showEmptyData()
            false -> view.showFavTeamList(favList)
        }
    }
}