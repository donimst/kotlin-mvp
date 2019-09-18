package com.hokagelab.doni.kadefootball.match.lastmatch

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.MatchEventResponse

class LastEvtPresenter(private val view: LastEvtView, private val apiRepository: ApiRepository) {

    fun getLastFixtureList(idLeague: Int) {
        view.showLoading()
        apiRepository.getLastFixture(idLeague, object : ApiRepositoryCallback<MatchEventResponse?> {
            override fun onGetResponse(dataResponse: MatchEventResponse?) {
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