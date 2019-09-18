package com.hokagelab.doni.kadefootball.match.search

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.SearchMatchResponse

class SearchResultPresenter(private val view: SearchResultView, private val apiRepository: ApiRepository) {

    fun searchMatch(keyword: String) {
        view.showLoading()
        apiRepository.searchFixture(keyword, object : ApiRepositoryCallback<SearchMatchResponse?> {
            override fun onGetResponse(dataResponse: SearchMatchResponse?) {
                view.onGetResponse(dataResponse)
                view.hideLoading()
            }

            override fun onGetError() {
                view.onGetError()
                view.hideLoading()
            }
        })

    }

}