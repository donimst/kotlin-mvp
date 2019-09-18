package com.hokagelab.doni.kadefootball.ui

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.hokagelab.doni.kadefootball.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PlayersUI : AnkoComponent<ViewGroup> {

    companion object {
        const val tvPlayerName = 1
        const val ivPlayerAvatar = 2
        const val tvPlayerPosition = 3
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            lparams(width = matchParent, height = wrapContent) {
                bottomMargin = dip(10)
            }

            cardView {
                maxCardElevation = 1.0f
                cardElevation = 1.0f
                preventCornerOverlap = true
                useCompatPadding = true
                radius = 10.0f

                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = tvPlayerPosition
                        backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        textColor = ContextCompat.getColor(context, R.color.white)
                        gravity = Gravity.CENTER
                        textSize = 12.0f
                    }.lparams(width = matchParent, height = wrapContent) {
                        leftMargin = dip(2)
                        rightMargin = dip(2)
                    }

                    imageView {
                        backgroundResource = R.color.white
                        id = ivPlayerAvatar
                    }.lparams(width = dip(72), height = dip(72)) {
                        gravity = Gravity.CENTER
                        setMargins(dip(10), dip(3), dip(10), dip(3))
                    }

                    textView {
                        id = tvPlayerName
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        lines = 3
                        gravity = Gravity.CENTER
                        textSize = 12.0f
                    }.lparams(width = matchParent, height = wrapContent) {
                        leftMargin = dip(2)
                        rightMargin = dip(2)
                    }
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = wrapContent, height = wrapContent) {
                margin = dip(0)
            }
        }
    }
}