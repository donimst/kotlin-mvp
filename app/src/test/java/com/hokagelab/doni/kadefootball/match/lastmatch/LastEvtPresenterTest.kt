package com.hokagelab.doni.kadefootball.match.lastmatch

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

class LastEvtPresenterTest {

    @Mock
    lateinit var view: LastEvtView
    @Mock
    lateinit var matchEventResponse: MatchEventResponse
    @Mock
    lateinit var api: ApiRepository

    lateinit var presenter: LastEvtPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastEvtPresenter(view, api)
    }

    @Test
    fun testGetLastFixtureList() {
        val id = 4335
        presenter.getLastFixtureList(id)

        argumentCaptor<ApiRepositoryCallback<MatchEventResponse?>>().apply {
            verify(api).getLastFixture(eq(id), capture())
            firstValue.onGetResponse(matchEventResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onGetResponse(matchEventResponse)
        Mockito.verify(view).hideLoading()
    }
}