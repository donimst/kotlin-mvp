package com.hokagelab.doni.kadefootball.ui

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.hokagelab.doni.kadefootball.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamsUI : AnkoComponent<ViewGroup> {

    companion object {
        const val tvTeamName = 1
        const val ivTeamLogo = 2
        const val cvTeam = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            lparams(width = matchParent, height = wrapContent) {
                bottomMargin = dip(10)
            }

            cardView {
                id = cvTeam
                maxCardElevation = 1.0f
                cardElevation = 1.0f
                preventCornerOverlap = true
                useCompatPadding = true
                radius = 10.0f

                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.VERTICAL

                    imageView {
                        backgroundResource = R.color.white
                        id = ivTeamLogo
                    }.lparams(width = dip(72), height = dip(72)) {
                        gravity = Gravity.CENTER
                        margin = dip(5)
                    }
                    textView {
                        id = tvTeamName
                        lines = 2
                        gravity = Gravity.CENTER
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        textSize = 13.0f
                    }.lparams(width = matchParent, height = wrapContent) {
                        leftMargin = dip(3)
                        rightMargin = dip(3)
                    }
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = wrapContent, height = wrapContent) {
                margin = dip(0)
            }
        }
    }
}