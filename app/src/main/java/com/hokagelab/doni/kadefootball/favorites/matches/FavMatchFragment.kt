package com.hokagelab.doni.kadefootball.favorites.matches

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.adapter.MatchEventAdapter
import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.match.DetailMatchActivity
import com.hokagelab.doni.kadefootball.model.MatchEvent
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavMatchFragment : Fragment(), AnkoComponent<Context>, FavMatchView {

    private lateinit var recView: RecyclerView
    private lateinit var presenter: FavMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView
    private lateinit var adapter: MatchEventAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var favMatchList = ArrayList<MatchEvent>()

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showFavMatchList(data: List<MatchEvent>) {
        emptyText.visibility = View.GONE
        recView.visibility = View.VISIBLE
        favMatchList = data as ArrayList<MatchEvent>
        adapter = MatchEventAdapter(data) {
            ctx.startActivity<DetailMatchActivity>(DetailMatchActivity.DETAIL_EXTRA to it)
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
        presenter = FavMatchPresenter(this, FavoriteRepository(ctx))
        if (savedInstanceState != null) {
            favMatchList = savedInstanceState.getParcelableArrayList<MatchEvent>("FAV_MATCH")
            showFavMatchList(favMatchList)
        } else {
            presenter.getFavoriteMatchList()
        }

        swipeRefresh.setOnRefreshListener {
            presenter.getFavoriteMatchList()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            swipeRefresh = swipeRefreshLayout {
                id = R.id.refresh_favMatch
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
                        id = R.id.recView_favMatch
                        layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                        lparams(width = matchParent, height = matchParent)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("FAV_MATCH", favMatchList)
        super.onSaveInstanceState(outState)
    }
}