package com.hokagelab.doni.kadefootball

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.SearchView
import android.view.*
import com.hokagelab.doni.kadefootball.match.lastmatch.LastEvtFragment
import com.hokagelab.doni.kadefootball.match.nextmatch.NextEvtFragment
import com.hokagelab.doni.kadefootball.match.search.SearchResultActivity
import kotlinx.android.synthetic.main.match_fragment.*
import org.jetbrains.anko.startActivity

class MatchFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        match_container.adapter = context?.let { MatchPagerAdapter(childFragmentManager, it) }
        match_tabs.setupWithViewPager(match_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchResultActivity>(SearchResultActivity.QUERY_EXTRA to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    class MatchPagerAdapter(fragmentManager: FragmentManager, context: Context) :
        FragmentPagerAdapter(fragmentManager) {

        var pageTitle = arrayOf(context.getString(R.string.last_match), context.getString(R.string.next_match))
        override fun getItem(pos: Int): Fragment {
            return when (pos) {
                0 -> LastEvtFragment()
                else -> NextEvtFragment()
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