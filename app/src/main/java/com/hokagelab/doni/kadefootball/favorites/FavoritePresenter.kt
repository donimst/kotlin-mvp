package com.hokagelab.doni.kadefootball.favorites

interface FavoritePresenter<T> {

    fun addFavorite(data: T)
    fun removeFavorite(id: Int)
    fun isFavorite(id: Int): Boolean

}