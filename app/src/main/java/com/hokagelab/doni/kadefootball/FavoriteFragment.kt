package com.hokagelab.doni.kadefootball

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hokagelab.doni.kadefootball.favorites.matches.FavMatchFragment
import com.hokagelab.doni.kadefootball.favorites.teams.FavTeamFragment
import kotlinx.android.synthetic.main.fav_fragment.*

class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fav_container.adapter = context?.let { FavoritePagerAdapter(childFragmentManager, it) }
        fav_tabs.setupWithViewPager(fav_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fav_fragment, container, false)
    }

    class FavoritePagerAdapter(fragmentManager: FragmentManager, context: Context) :
        FragmentPagerAdapter(fragmentManager) {

        var pageTitle = arrayOf(context.getString(R.string.match), context.getString(R.string.teams))
        override fun getItem(pos: Int): Fragment {
            return when (pos) {
                0 -> FavMatchFragment()
                else -> FavTeamFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pageTitle[position]
        }

        override fun getCount(): Int {
            return 2
        }
    }
}