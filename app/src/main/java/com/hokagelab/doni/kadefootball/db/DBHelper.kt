package com.hokagelab.doni.kadefootball.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hokagelab.doni.kadefootball.BuildConfig
import org.jetbrains.anko.db.*
import com.hokagelab.doni.kadefootball.model.Favorites.Match as FavMatch
import com.hokagelab.doni.kadefootball.model.Favorites.Team as FavTeam

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, BuildConfig.DB_NAME, null, 1) {

    companion object {
        private var dbInstance: DBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if (dbInstance == null) dbInstance = DBHelper(ctx.applicationContext)
            return dbInstance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavMatch.TB_FAVORITE_MATCH, true,
            FavMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavMatch.EVENT_ID to INTEGER + UNIQUE,
            FavMatch.LEAGUE to TEXT,
            FavMatch.ROUND to INTEGER,
            FavMatch.DATE to TEXT,
            FavMatch.TIME to TEXT,
            FavMatch.HOME_ID to INTEGER,
            FavMatch.AWAY_ID to INTEGER,
            FavMatch.HOME_NAME to TEXT,
            FavMatch.AWAY_NAME to TEXT,
            FavMatch.HOME_SCORE to INTEGER,
            FavMatch.AWAY_SCORE to INTEGER,
            FavMatch.HOME_SHOTS to INTEGER,
            FavMatch.AWAY_SHOTS to INTEGER,
            FavMatch.HOME_GOALS to TEXT,
            FavMatch.AWAY_GOALS to TEXT,
            FavMatch.HOME_YC to TEXT,
            FavMatch.AWAY_YC to TEXT,
            FavMatch.HOME_RC to TEXT,
            FavMatch.AWAY_RC to TEXT,
            FavMatch.HOME_GK to TEXT,
            FavMatch.HOME_DF to TEXT,
            FavMatch.HOME_MF to TEXT,
            FavMatch.HOME_FW to TEXT,
            FavMatch.HOME_SUBS to TEXT,
            FavMatch.AWAY_GK to TEXT,
            FavMatch.AWAY_DF to TEXT,
            FavMatch.AWAY_MF to TEXT,
            FavMatch.AWAY_FW to TEXT,
            FavMatch.AWAY_SUBS to TEXT
        )

        db.createTable(
            FavTeam.TB_FAVORITE_TEAM, true,
            FavTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavTeam.TEAM_ID to INTEGER,
            FavTeam.TEAM_NAME to TEXT,
            FavTeam.TEAM_BADGE to TEXT,
            FavTeam.TEAM_FANART to TEXT,
            FavTeam.TEAM_FORMED_YEAR to INTEGER,
            FavTeam.TEAM_MANAGER to TEXT,
            FavTeam.TEAM_STADIUM to TEXT,
            FavTeam.TEAM_LOCATION to TEXT,
            FavTeam.TEAM_COUNTRY to TEXT,
            FavTeam.TEAM_STD_CAP to INTEGER,
            FavTeam.TEAM_DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, v1: Int, v2: Int) {
        db.dropTable(FavMatch.TB_FAVORITE_MATCH, true)
        db.dropTable(FavTeam.TB_FAVORITE_TEAM, true)
    }
}

val Context.database: DBHelper
    get() = DBHelper.getInstance(applicationContext)