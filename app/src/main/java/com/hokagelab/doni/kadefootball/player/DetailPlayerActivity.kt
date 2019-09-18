package com.hokagelab.doni.kadefootball.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.model.Players
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_player.*
import kotlinx.android.synthetic.main.detail_player.*


class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {

    companion object {
        val PLAYER_DETAIL_EXTRA = "PLAYER_DETAIL_EXTRA"
        val PLAYER = "PLAYER"
    }

    private lateinit var players: Players
    private lateinit var presenter: DetailPlayerPresenter

    override fun showData(player: Players) {
        tvPlayerName.text = player.playerName + " (" + player.playerPosition + ")"
        tvPlayerHeight.text = player.playerHeight.toString() + " m"
        tvPlayerWeight.text = player.playerWeight.toString() + " kg"
        tvPlayerDesc.text = player.playerDescription
    }

    override fun initCollapsingBar(player: Players) {
        Picasso.get()
            .load(player.playerBanner)
            .error(R.color.colorPrimary)
            .into(bannerPlayer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_player)
        setSupportActionBar(toolbarPlayer)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailPlayerPresenter(this)

        when (savedInstanceState != null) {
            true -> {
                players = savedInstanceState.getParcelable(PLAYER)
            }
            false -> {
                players = intent.getParcelableExtra<Players>(PLAYER_DETAIL_EXTRA)
            }
        }

        presenter.loadPlayerData(players)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(PLAYER, players)
    }

}
