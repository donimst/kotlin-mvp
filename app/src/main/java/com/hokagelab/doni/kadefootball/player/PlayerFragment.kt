package com.hokagelab.doni.kadefootball.player

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.adapter.PlayersAdapter
import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.model.Players
import com.hokagelab.doni.kadefootball.model.PlayersResponse
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.team.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class PlayerFragment : Fragment(), AnkoComponent<Context>, PlayerView {

    private lateinit var recView: RecyclerView
    private lateinit var presenter: PlayerPresenter
    private lateinit var emptyText: TextView
    private var playerList = ArrayList<Players>()

    override fun onGetResponse(dataResponse: PlayersResponse?) {
        playerList = dataResponse?.playerList as ArrayList<Players>
        showPlayerList(playerList)
    }

    override fun onGetError() {

    }

    override fun showPlayerList(data: List<Players>) {
        recView.adapter = PlayersAdapter(data) {
            ctx.startActivity<DetailPlayerActivity>(DetailPlayerActivity.PLAYER_DETAIL_EXTRA to it)
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team = arguments?.getParcelable<Teams>(DetailTeamActivity.TEAM_DETAIL)
        presenter = PlayerPresenter(this, ApiRepository())
        if (savedInstanceState != null) {
            playerList = savedInstanceState.getParcelableArrayList("TEAM_PLAYER")
            showPlayerList(playerList)
        } else {
            team?.teamID?.let { presenter.getTeamPlayersList(it) }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            verticalLayout {
                padding = dip(16)
                lparams(matchParent, wrapContent)

                emptyText = textView {
                    text = ctx.resources.getString(R.string.empty)
                    textSize = 16f
                    visibility = View.GONE
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER
                }

                recView = recyclerView {
                    id = R.id.recView_player
                    layoutManager = GridLayoutManager(ctx, 4) as RecyclerView.LayoutManager?
                    lparams(width = matchParent, height = matchParent)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("TEAM_PLAYER", playerList)
    }
}