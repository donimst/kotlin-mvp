package com.hokagelab.doni.kadefootball.team

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.db.FavoriteRepository
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.player.PlayerFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_team.*


class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    companion object {
        val DETAIL_EXTRA = "TEAM_DETAIL_EXTRA"
        val TEAM_DETAIL = "TEAM_DETAIL"
    }

    private lateinit var teams: Teams
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var vpAdapter: TeamPagerAdapter
    private val bundle = Bundle()
    private val ovwFragment = OverviewFragment()
    private val plyrFragment = PlayerFragment()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun initCollapsingBar(team: Teams) {
        Picasso.get()
            .load(team.teamFanart)
            .error(R.color.colorPrimary)
            .into(bannerTeam)

        app_bar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsing_toolbar.title = team.teamName
                    isShow = true
                } else if (isShow) {
                    collapsing_toolbar.title = ""
                    isShow = false
                }
            }
        });
    }

    fun initContentData() {
        bundle.putParcelable(TEAM_DETAIL, teams)
        vpAdapter = TeamPagerAdapter(supportFragmentManager, this)
        ovwFragment.arguments = bundle
        plyrFragment.arguments = bundle
        vpAdapter.setFragment(ovwFragment)
        vpAdapter.setFragment(plyrFragment)
        viewPagerTeam.adapter = vpAdapter
        tabLayoutTeam.setupWithViewPager(viewPagerTeam)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_team)
        setSupportActionBar(toolbarTeam)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailTeamPresenter(this, FavoriteRepository(this))

        when (savedInstanceState != null) {
            true -> teams = savedInstanceState.getParcelable(TEAM_DETAIL)
            false -> teams = intent.getParcelableExtra<Teams>(DetailTeamActivity.DETAIL_EXTRA)
        }
        initCollapsingBar(teams)
        initContentData()
        isFavorite = presenter.isFavorite(teams.teamID)

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(TEAM_DETAIL, teams)
    }

    private fun addToFav() {
        when (isFavorite) {
            true -> presenter.removeFavorite(teams.teamID)
            false -> presenter.addFavorite(teams)
        }
        isFavorite = !isFavorite
    }

    private fun toggleFav() {
        when (isFavorite) {
            true -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_remove)
            false -> menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add)
        }
    }

    class TeamPagerAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

        var pageTitle = arrayOf(context.getString(R.string.overview), context.getString(R.string.players))
        var fragmentList = arrayListOf<Fragment>()

        fun setFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItem(pos: Int): Fragment {
            return fragmentList[pos]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pageTitle[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

    }
}
