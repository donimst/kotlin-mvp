package com.hokagelab.doni.kadefootball.favorites.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.adapter.TeamsAdapter
import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.team.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavTeamFragment : Fragment(), AnkoComponent<Context>, FavTeamView {

    private lateinit var recView: RecyclerView
    private lateinit var presenter: FavTeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView
    private lateinit var adapter: TeamsAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var favTeamList = ArrayList<Teams>()

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showFavTeamList(data: List<Teams>?) {
        emptyText.visibility = View.GONE
        recView.visibility = View.VISIBLE
        favTeamList = data as ArrayList<Teams>
        adapter = TeamsAdapter(data) {
            ctx.startActivity<DetailTeamActivity>(DetailTeamActivity.DETAIL_EXTRA to it)
        }
        recView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyData() {
        emptyText.visibility = View.VISIBLE
        recView.visibility = View.GONE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FavTeamPresenter(this, FavoriteRepository(ctx))
        if (savedInstanceState != null) {
            favTeamList = savedInstanceState.getParcelableArrayList<Teams>("FAV_TEAM")
            showFavTeamList(favTeamList)
        } else {
            presenter.getFavoriteTeamList()
        }

        swipeRefresh.setOnRefreshListener {
            presenter.getFavoriteTeamList()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            swipeRefresh = swipeRefreshLayout {
                id = R.id.refresh_favTeam
                verticalLayout {
                    padding = dip(16)
                    lparams(matchParent, wrapContent)

                    progressBar = progressBar {
                        visibility = View.GONE
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(8)
                    }

                    emptyText = textView {
                        id = R.id.empty_text
                        text = ctx.resources.getString(R.string.empty)
                        textSize = 16f
                        visibility = View.GONE
                    }.lparams(wrapContent, wrapContent) {
                        gravity = Gravity.CENTER
                    }

                    recView = recyclerView {
                        id = R.id.recView_favTeam
                        layoutManager = GridLayoutManager(ctx, 4)
                        lparams(width = matchParent, height = matchParent)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("FAV_TEAM", favTeamList)
        super.onSaveInstanceState(outState)
    }
}