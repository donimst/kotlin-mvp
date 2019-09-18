package com.hokagelab.doni.kadefootball.team

import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.favorites.FavoritePresenter
import com.hokagelab.doni.kadefootball.model.Teams

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val favoriteRepository: FavoriteRepository
) :
    FavoritePresenter<Teams> {

    override fun addFavorite(team: Teams) {
        favoriteRepository.insertTeam(team)
    }

    override fun removeFavorite(idTeam: Int) {
        favoriteRepository.deleteTeam(idTeam)
    }

    override fun isFavorite(idTeam: Int): Boolean {
        return favoriteRepository.checkTeam(idTeam).isNotEmpty()
    }


}