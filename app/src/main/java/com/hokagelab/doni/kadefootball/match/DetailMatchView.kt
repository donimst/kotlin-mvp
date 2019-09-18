package com.hokagelab.doni.kadefootball.match

import com.hokagelab.doni.kadefootball.model.MatchEvent

interface DetailMatchView {

    fun showHomeBadge(strHomeBadge: String)
    fun showAwayBadge(strAwayBadge: String)
    fun showData(matchEvent: MatchEvent)
}