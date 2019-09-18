package com.hokagelab.doni.kadefootball.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.model.Teams
import kotlinx.android.synthetic.main.overview_fragment.*

class OverviewFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team = arguments?.getParcelable<Teams>(DetailTeamActivity.TEAM_DETAIL)
        loadTeamOverview(team)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    private fun loadTeamOverview(team: Teams?) {
        teamName.text = team?.teamName
        tvFormedYear.text = team?.teamFormedYear.toString()
        tvManager.text = team?.teamManager
        tvStadium.text = team?.teamStadium + "(" + team?.teamStdCapacity.toString() + "), \n" + team?.teamLocation +
                ", " + team?.teamCountry
        tvOverview.text = team?.teamDescription
    }

}