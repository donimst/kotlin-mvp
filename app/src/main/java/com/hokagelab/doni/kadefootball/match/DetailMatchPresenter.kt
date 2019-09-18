package com.hokagelab.doni.kadefootball.match

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.favorites.FavoritePresenter
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.TeamsResponse
import com.hokagelab.doni.kadefootball.model.Favorites.Match as FavMatch

class DetailMatchPresenter(
    private val view: DetailMatchView,
    private val apiRepository: ApiRepository,
    private val favoriteRepository: FavoriteRepository
) :
    FavoritePresenter<MatchEvent> {

    override fun addFavorite(data: MatchEvent) {
        favoriteRepository.insertMatch(data)
    }

    override fun removeFavorite(idEvent: Int) {
        favoriteRepository.deleteMatch(idEvent)
    }

    override fun isFavorite(idEvent: Int): Boolean {
        return favoriteRepository.checkMatch(idEvent).isNotEmpty()
    }

    fun getHomeTeamBadge(idTeam: Int) {
        apiRepository.getTeams(idTeam, object : ApiRepositoryCallback<TeamsResponse?> {
            override fun onGetResponse(dataResponse: TeamsResponse?) {
                view.showHomeBadge(dataResponse?.teamList?.get(0)?.teamBadge.toString())
            }

            override fun onGetError() {
            }
        })
    }

    fun getAwayTeamBadge(idTeam: Int) {
        apiRepository.getTeams(idTeam, object : ApiRepositoryCallback<TeamsResponse?> {
            override fun onGetResponse(dataResponse: TeamsResponse?) {
                view.showAwayBadge(dataResponse?.teamList?.get(0)?.teamBadge.toString())
            }

            override fun onGetError() {
            }
        })
    }

}