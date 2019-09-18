package com.hokagelab.doni.kadefootball

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.hokagelab.doni.kadefootball.adapter.TeamsAdapter
import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.model.TeamsResponse
import com.hokagelab.doni.kadefootball.team.DetailTeamActivity
import com.hokagelab.doni.kadefootball.team.TeamPresenter
import com.hokagelab.doni.kadefootball.team.TeamView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var recView: RecyclerView
    private lateinit var presenter: TeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyText: TextView
    private lateinit var adapter: TeamsAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueIDs: IntArray
    private var teamsList = ArrayList<Teams>()
    private var positionItem = 0

    override fun onGetResponse(dataResponse: TeamsResponse?) {
        showTeamList(dataResponse?.teamList)
    }

    override fun onGetError() {
        showEmptyData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = TeamPresenter(this, ApiRepository())
        leagueIDs = resources.getIntArray(R.array.league_key)
        when (savedInstanceState != null) {
            true -> {
                positionItem = savedInstanceState.getInt("LAST_SELECTED")
                spinner.setSelection(positionItem)
                teamsList = savedInstanceState.getParcelableArrayList<Teams>("TEAM")
                showTeamList(teamsList)
            }
            false -> presenter.getTeamsList(leagueIDs[positionItem])
        }
        swipeRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        presenter.getTeamsList(leagueIDs[positionItem])
        swipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recView.visibility = View.GONE
        emptyText.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<Teams>?) {
        emptyText.visibility = View.GONE
        recView.visibility = View.VISIBLE
        teamsList = data as ArrayList<Teams>
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

                    spinner = spinner {
                        prompt = resources.getString(R.string.select_league)
                        backgroundDrawable = resources.getDrawable(R.drawable.bordered_bg)
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
                                    presenter.getTeamsList(leagueIDs[position])
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
                        id = R.id.empty_text
                        text = ctx.resources.getString(R.string.empty)
                        textSize = 16f
                        visibility = View.GONE
                    }.lparams(wrapContent, wrapContent) {
                        gravity = Gravity.CENTER
                    }

                    recView = recyclerView {
                        id = R.id.recView_team
                        layoutManager = GridLayoutManager(ctx, 4) as RecyclerView.LayoutManager?
                        lparams(width = matchParent, height = matchParent)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.searchTeams(newText)
                return false
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("TEAM", teamsList)
        outState.putInt("LAST_SELECTED", positionItem)
    }
}