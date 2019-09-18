package com.hokagelab.doni.kadefootball.match.search

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.adapter.MatchEventAdapter
import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.match.DetailMatchActivity
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.SearchMatchResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchResultActivity : AppCompatActivity(), AnkoComponent<Context>, SearchResultView {

    private lateinit var recView: RecyclerView
    private lateinit var presenter: SearchResultPresenter
    private lateinit var adapter: MatchEventAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var matchList = ArrayList<MatchEvent>()

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onGetResponse(dataResponse: SearchMatchResponse?) {
        showFixtureList(dataResponse?.eventList)
    }

    override fun onGetError() {
        showEmptyData()
    }

    override fun showFixtureList(data: List<MatchEvent>?) {
        emptyText.visibility = View.GONE
        recView.visibility = View.VISIBLE
        matchList = data as ArrayList<MatchEvent>
        adapter = MatchEventAdapter(data) {
            applicationContext.startActivity<DetailMatchActivity>(DetailMatchActivity.DETAIL_EXTRA to it)
        }
        recView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyData() {
        emptyText.visibility = View.VISIBLE
        recView.visibility = View.GONE
    }

    companion object {
        val QUERY_EXTRA = "QUERY_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this)
        supportActionBar?.title = ""
        presenter = SearchResultPresenter(this, ApiRepository())
        when (savedInstanceState != null) {
            true -> {
                matchList = savedInstanceState.getParcelableArrayList("RESULT_LIST")
                showFixtureList(matchList)
            }
            false -> presenter.searchMatch(intent.getStringExtra(QUERY_EXTRA))
        }

        swipeRefresh.setOnRefreshListener {
            presenter.searchMatch(intent.getStringExtra(QUERY_EXTRA))
            swipeRefresh.isRefreshing = false
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("RESULT_LIST", matchList)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            swipeRefresh = swipeRefreshLayout {
                verticalLayout {
                    padding = dip(16)
                    lparams(matchParent, wrapContent)

                    progressBar = progressBar {
                        visibility = View.GONE
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(8)
                    }

                    emptyText = textView {
                        text = ctx.resources.getString(R.string.empty)
                        textSize = 16f
                        visibility = View.GONE
                    }.lparams(wrapContent, wrapContent)

                    recView = recyclerView {
                        id = R.id.recView_result
                        layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                        lparams(width = matchParent, height = matchParent)
                    }
                }
            }
        }
    }

}