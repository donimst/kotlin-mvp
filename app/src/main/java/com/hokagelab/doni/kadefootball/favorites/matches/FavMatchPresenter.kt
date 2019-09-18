package com.hokagelab.doni.kadefootball.favorites.matches

import com.hokagelab.doni.kadefootball.db.FavoriteRepository

class FavMatchPresenter(private val view: FavMatchView, private val favoriteRepository: FavoriteRepository) {

    fun getFavoriteMatchList() {
        view.showLoading()
        val favList = favoriteRepository.getFavMatch()
        view.hideLoading()
        when (favList.isEmpty()) {
            true -> view.showEmptyData()
            false -> view.showFavMatchList(favList)
        }
    }
}