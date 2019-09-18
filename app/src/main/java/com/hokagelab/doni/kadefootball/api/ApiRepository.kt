package com.hokagelab.doni.kadefootball.api

import com.hokagelab.doni.kadefootball.model.MatchEventResponse
import com.hokagelab.doni.kadefootball.model.PlayersResponse
import com.hokagelab.doni.kadefootball.model.SearchMatchResponse
import com.hokagelab.doni.kadefootball.model.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository {

    fun getLastFixture(idLeague: Int, callback: ApiRepositoryCallback<MatchEventResponse?>) {
        ApiService.create()
            .getLastFixture(idLeague)
            .enqueue(object : Callback<MatchEventResponse?> {
                override fun onFailure(call: Call<MatchEventResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<MatchEventResponse?>?, response: Response<MatchEventResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun getNextFixture(idLeague: Int, callback: ApiRepositoryCallback<MatchEventResponse?>) {
        ApiService.create()
            .getNextFixture(idLeague)
            .enqueue(object : Callback<MatchEventResponse?> {
                override fun onFailure(call: Call<MatchEventResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<MatchEventResponse?>?, response: Response<MatchEventResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun searchFixture(keyword: String, callback: ApiRepositoryCallback<SearchMatchResponse?>) {
        ApiService.create()
            .searchFixture(keyword, 1819)
            .enqueue(object : Callback<SearchMatchResponse?> {
                override fun onFailure(call: Call<SearchMatchResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<SearchMatchResponse?>?, response: Response<SearchMatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun getTeams(idTeam: Int, callback: ApiRepositoryCallback<TeamsResponse?>) {
        ApiService.create()
            .getTeams(idTeam)
            .enqueue(object : Callback<TeamsResponse?> {
                override fun onFailure(call: Call<TeamsResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<TeamsResponse?>?, response: Response<TeamsResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun getAllTeams(idLeague: Int, callback: ApiRepositoryCallback<TeamsResponse?>) {
        ApiService.create()
            .getAllTeams(idLeague)
            .enqueue(object : Callback<TeamsResponse?> {
                override fun onFailure(call: Call<TeamsResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<TeamsResponse?>?, response: Response<TeamsResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun getTeamPlayers(idTeam: Int, callback: ApiRepositoryCallback<PlayersResponse?>) {
        ApiService.create()
            .getTeamPlayers(idTeam)
            .enqueue(object : Callback<PlayersResponse?> {
                override fun onFailure(call: Call<PlayersResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<PlayersResponse?>?, response: Response<PlayersResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }

    fun searchTeams(teamName: String, callback: ApiRepositoryCallback<TeamsResponse?>) {
        ApiService.create()
            .searchTeams(teamName)
            .enqueue(object : Callback<TeamsResponse?> {
                override fun onFailure(call: Call<TeamsResponse?>?, t: Throwable?) {
                    callback.onGetError()
                }

                override fun onResponse(call: Call<TeamsResponse?>?, response: Response<TeamsResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onGetResponse(it.body())
                        } else {
                            callback.onGetError()
                        }
                    }
                }
            })
    }


}