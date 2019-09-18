package com.hokagelab.doni.kadefootball.api

interface ApiRepositoryCallback<T> {

    fun onGetResponse(dataResponse: T?)
    fun onGetError()
}