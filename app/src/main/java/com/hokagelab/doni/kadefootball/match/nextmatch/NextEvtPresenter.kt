package com.hokagelab.doni.kadefootball.match.nextmatch

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.MatchEventResponse

class NextEvtPresenter(private val view: NextEvtView, private val apiRepository: ApiRepository) {

    fun getNextFixtureList(idLeague: Int) {
        view.showLoading()
        apiRepository.getNextFixture(idLeague, object : ApiRepositoryCallback<MatchEventResponse?> {
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