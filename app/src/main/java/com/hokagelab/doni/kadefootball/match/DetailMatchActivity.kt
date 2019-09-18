package com.hokagelab.doni.kadefootball.match

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    companion object {
        val DETAIL_EXTRA = "MATCH_DETAIL_EXTRA"
        val MATCH_DETAIL = "MATCH_DETAIL"
    }

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var matchEvent: MatchEvent
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun showData(data: MatchEvent) {
        homeScore.text = data.homeScore?.toString() ?: "-"
        awayScore.text = data.awayScore?.toString() ?: "-"
        homeTeam.text = data.homeTeam
        awayTeam.text = data.awayTeam
        homeShot.text = data.homeShots?.toString() ?: "-"
        awayShot.text = data.awayShots?.toString() ?: "-"
        homeGoals.text = data.homeGoal?.replace(";", "\n") ?: "-"
        awayGoals.text = data.awayGoal?.replace(";", "\n") ?: "-"
        homeYC.text = data.homeYellow?.replace(";", "\n") ?: "-"
        awayYC.text = data.awayYellow?.replace(";", "\n") ?: "-"
        homeRC.text = data.homeRed?.replace(";", "\n") ?: "-"
        awayRC.text = data.awayRed?.replace(";", "\n") ?: "-"
        homeGK.text = data.homeGK?.replace(";", "\n") ?: "-"
        awayGK.text = data.awayGK?.replace(";", "\n") ?: "-"
        homeDF.text = data.homeDF?.replace("; ", "\n") ?: "-"
        awayDF.text = data.awayDF?.replace("; ", "\n") ?: "-"
        homeMF.text = data.homeMF?.replace("; ", "\n") ?: "-"
        awayMF.text = data.awayMF?.replace("; ", "\n") ?: "-"
        homeFW.text = data.homeFW?.replace("; ", "\n") ?: "-"
        awayFW.text = data.awayFW?.replace("; ", "\n") ?: "-"
        homeSubs.text = data.homeSubs?.replace("; ", "\n") ?: "-"
        awaySubs.text = data.awaySubs?.replace("; ", "\n") ?: "-"
    }

    override fun showHomeBadge(strHomeBadge: String) {
        Picasso.get()
            .load(strHomeBadge)
            .error(R.drawable.ic_launcher_background)
            .into(homeBadge)
    }

    override fun showAwayBadge(strAwayBadge: String) {
        Picasso.get()
            .load(strAwayBadge)
            .error(R.drawable.ic_launcher_background)
            .into(awayBadge)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseContext.resources
        setContentView(R.layout.detail_match)

        presenter = DetailMatchPresenter(this, ApiRepository(), FavoriteRepository(this))

        when (savedInstanceState != null) {
            true -> matchEvent = savedInstanceState.getParcelable(MATCH_DETAIL)
            false -> matchEvent = intent.getParcelableExtra<MatchEvent>(DETAIL_EXTRA)
        }

        presenter.getHomeTeamBadge(matchEvent.idHome)
        presenter.getAwayTeamBadge(matchEvent.idAway)
        isFavorite = presenter.isFavorite(matchEvent.idEvent)
        showData(matchEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        menuItem = menu
        toggleFav()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                addToFav()
                toggleFav()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFav() {
        when (isFavorite) {
            true -> presenter.removeFavorite(matchEvent.idEvent)
            false -> presenter.addFavorite(matchEvent)
        }
        isFavorite = !isFavorite
    }

    private fun toggleFav() {
        when (isFavorite) {
            true -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_remove
            )
            false -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_add
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(MATCH_DETAIL, matchEvent)
    }

}
