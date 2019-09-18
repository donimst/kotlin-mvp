package com.hokagelab.doni.kadefootball.match.lastmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.adapter.MatchEventAdapter
import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.match.DetailMatchActivity
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.MatchEventResponse
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastEvtFragment : Fragment(), AnkoComponent<Context>, LastEvtView {

    override fun onGetResponse(dataResponse: MatchEventResponse?) {
        showFixtureList(dataResponse?.eventList)
    }

    override fun onGetError() {
        showEmptyData()
    }

    private lateinit var recView: RecyclerView
    private lateinit var presenter: LastEvtPresenter
    private lateinit var adapter: MatchEventAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var emptyText: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueIDs: IntArray
    private var positionItem = 0
    private var matchList = ArrayList<MatchEvent>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = LastEvtPresenter(this, ApiRepository())
        leagueIDs = resources.getIntArray(R.array.league_key)
        when (savedInstanceState != null) {
            true -> {
                positionItem = savedInstanceState.getInt("LAST_SELECTED")
                spinner.setSelection(positionItem)
                matchList = savedInstanceState.getParcelableArrayList<MatchEvent>("LAST_MATCH")
                showFixtureList(matchList)
            }
            false -> presenter.getLastFixtureList(leagueIDs[positionItem])
        }

        swipeRefresh.setOnRefreshListener {
            presenter.getLastFixtureList(leagueIDs[positionItem])
            swipeRefresh.isRefreshing = false
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showFixtureList(data: List<MatchEvent>?) {
        emptyText.visibility = View.GONE
        recView.visibility = View.VISIBLE
        matchList = data as ArrayList<MatchEvent>
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            swipeRefresh = swipeRefreshLayout {
                verticalLayout {
                    padding = dip(16)
                    lparams(matchParent, wrapContent)

                    spinner = spinner {
                        prompt = resources.getString(R.string.select_league)
                        adapter = ArrayAdapter(
                            ctx,
                            R.layout.support_simple_spinner_dropdown_item,
                            resources.getStringArray(R.array.league_list)
                        )
                        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }

                            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                if (positionItem != position) {
                                    positionItem = position
                                    presenter.getLastFixtureList(leagueIDs[position])
                                }
                            }

                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        bottomMargin = dip(8)
                    }

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
                        id = R.id.recView_last
                        layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                        lparams(width = matchParent, height = matchParent)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("LAST_MATCH", matchList)
        outState.putInt("LAST_SELECTED", positionItem)
    }

}