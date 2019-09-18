package com.hokagelab.doni.kadefootball.db

import android.content.Context
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.Teams
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import com.hokagelab.doni.kadefootball.model.Favorites.Match as FavMatch
import com.hokagelab.doni.kadefootball.model.Favorites.Team as FavTeam

class FavoriteRepository(private val ctx: Context) {

    fun insertMatch(data: MatchEvent) {
        ctx.database.use {
            insert(
                FavMatch.TB_FAVORITE_MATCH,
                FavMatch.EVENT_ID to data.idEvent,
                FavMatch.LEAGUE to data.league,
                FavMatch.ROUND to data.round,
                FavMatch.DATE to data.strDate,
                FavMatch.TIME to data.strTime,
                FavMatch.HOME_ID to data.idHome,
                FavMatch.AWAY_ID to data.idAway,
                FavMatch.HOME_NAME to data.homeTeam,
                FavMatch.AWAY_NAME to data.awayTeam,
                FavMatch.HOME_SCORE to data.homeScore,
                FavMatch.AWAY_SCORE to data.awayScore,
                FavMatch.HOME_SHOTS to data.homeShots,
                FavMatch.AWAY_SHOTS to data.awayShots,
                FavMatch.HOME_GOALS to data.homeGoal,
                FavMatch.AWAY_GOALS to data.awayGoal,
                FavMatch.HOME_YC to data.homeYellow,
                FavMatch.AWAY_YC to data.awayYellow,
                FavMatch.HOME_RC to data.homeRed,
                FavMatch.AWAY_RC to data.awayRed,
                FavMatch.HOME_GK to data.homeGK,
                FavMatch.HOME_DF to data.homeDF,
                FavMatch.HOME_MF to data.homeMF,
                FavMatch.HOME_FW to data.homeFW,
                FavMatch.HOME_SUBS to data.homeSubs,
                FavMatch.AWAY_GK to data.awayGK,
                FavMatch.AWAY_DF to data.awayDF,
                FavMatch.AWAY_MF to data.awayMF,
                FavMatch.AWAY_FW to data.awayFW,
                FavMatch.AWAY_SUBS to data.awaySubs
            )
        }
    }

    fun insertTeam(data: Teams) {
        ctx.database.use {
            insert(
                FavTeam.TB_FAVORITE_TEAM,
                FavTeam.TEAM_ID to data.teamID,
                FavTeam.TEAM_NAME to data.teamName,
                FavTeam.TEAM_BADGE to data.teamBadge,
                FavTeam.TEAM_FANART to data.teamFanart,
                FavTeam.TEAM_FORMED_YEAR to data.teamFormedYear,
                FavTeam.TEAM_MANAGER to data.teamManager,
                FavTeam.TEAM_STADIUM to data.teamStadium,
                FavTeam.TEAM_LOCATION to data.teamLocation,
                FavTeam.TEAM_COUNTRY to data.teamCountry,
                FavTeam.TEAM_STD_CAP to data.teamStdCapacity,
                FavTeam.TEAM_DESCRIPTION to data.teamDescription
            )
        }
    }

    fun deleteMatch(idEvent: Int) {
        ctx.database.use {
            delete(
                FavMatch.TB_FAVORITE_MATCH,
                "(" + FavMatch.EVENT_ID + " = {evtID})",
                "evtID" to idEvent
            )
        }
    }

    fun deleteTeam(idTeam: Int) {
        ctx.database.use {
            delete(
                FavTeam.TB_FAVORITE_TEAM,
                "(" + FavTeam.TEAM_ID + " = {teamID})",
                "teamID" to idTeam
            )
        }
    }

    fun checkMatch(idEvent: Int): List<MatchEvent> {
        return ctx.database.use {
            val res = select(
                FavMatch.TB_FAVORITE_MATCH
            )
                .whereArgs(
                    "(" + FavMatch.EVENT_ID + " = {evtID})",
                    "evtID" to idEvent
                )
            val favorites = res.parseList(classParser<MatchEvent>())
            favorites
        }
    }

    fun checkTeam(idTeam: Int): List<Teams> {
        return ctx.database.use {
            val res = select(
                FavTeam.TB_FAVORITE_TEAM
            )
                .whereArgs(
                    "(" + FavTeam.TEAM_ID + " = {teamID})",
                    "teamID" to idTeam
                )
            val favorites = res.parseList(classParser<Teams>())
            favorites
        }
    }

    fun getFavMatch(): List<MatchEvent> {
        return ctx.database.use {
            val result = select(FavMatch.TB_FAVORITE_MATCH)
            val favList = result.parseList(classParser<MatchEvent>())
            favList
        }
    }

    fun getFavTeam(): List<Teams> {
        return ctx.database.use {
            val result = select(FavTeam.TB_FAVORITE_TEAM)
            val favList = result.parseList(classParser<Teams>())
            favList
        }
    }
}