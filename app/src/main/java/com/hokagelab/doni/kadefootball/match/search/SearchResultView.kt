package com.hokagelab.doni.kadefootball.match.search

import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.model.SearchMatchResponse

interface SearchResultView : ApiRepositoryCallback<SearchMatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showFixtureList(data: List<MatchEvent>?)
    fun showEmptyData()
}