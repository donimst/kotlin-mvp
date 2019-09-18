package com.hokagelab.doni.kadefootball.match.nextmatch

import com.hokagelab.doni.kadefootball.api.ApiRepository
import com.hokagelab.doni.kadefootball.api.ApiRepositoryCallback
import com.hokagelab.doni.kadefootball.model.MatchEventResponse
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextEvtPresenterTest {

    @Mock
    lateinit var view: NextEvtView
    @Mock
    lateinit var matchEventResponse: MatchEventResponse
    @Mock
    lateinit var api: ApiRepository

    lateinit var presenter: NextEvtPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextEvtPresenter(view, api)
    }

    @Test
    fun testGetNextFixtureList() {
        val id = 4335
        presenter.getNextFixtureList(id)

        argumentCaptor<ApiRepositoryCallback<MatchEventResponse?>>().apply {
            verify(api).getNextFixture(eq(id), capture())
            firstValue.onGetResponse(matchEventResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onGetResponse(matchEventResponse)
        Mockito.verify(view).hideLoading()
    }
}