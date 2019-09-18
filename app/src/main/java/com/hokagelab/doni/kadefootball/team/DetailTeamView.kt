package com.hokagelab.doni.kadefootball.team

import com.hokagelab.doni.kadefootball.model.Teams

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun initCollapsingBar(data: Teams)
}