package com.hokagelab.doni.kadefootball

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = resources.getString(R.string.match)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, MatchFragment(), MatchFragment::class.java.simpleName)
            .commit()
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = resources.getString(R.string.teams)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, TeamsFragment(), TeamsFragment::class.java.simpleName)
            .commit()
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        supportActionBar?.title = resources.getString(R.string.fav)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_content, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseContext.resources
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.matchMenu -> loadMatchFragment(savedInstanceState)
                R.id.teamsMenu -> loadTeamsFragment(savedInstanceState)
                R.id.favMenu -> loadFavoriteFragment(savedInstanceState)
            }
            true
        }

        if (savedInstanceState == null) {
            bottom_nav.selectedItemId = R.id.matchMenu
        }
    }

}
