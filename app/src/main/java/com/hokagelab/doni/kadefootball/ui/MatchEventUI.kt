package com.hokagelab.doni.kadefootball.ui

import android.graphics.Typeface
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.hokagelab.doni.kadefootball.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class MatchEventUI : AnkoComponent<ViewGroup> {

    companion object {
        const val tvRound = 1
        const val dtTime = 2
        const val divider = 3
        const val tvHome = 4
        const val tvAway = 5
        const val tvVersus = 6
        const val tvHomeScore = 7
        const val tvAwayScore = 8
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            verticalLayout {
                backgroundResource = R.drawable.bordered_bg
                gravity = Gravity.CENTER_HORIZONTAL
                leftPadding = dip(5)
                rightPadding = dip(5)
                bottomPadding = dip(5)
                topPadding = dip(5)
                textView {
                    id = tvRound
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textView {
                    id = dtTime
                    textColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textSize = 12f
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                view {
                    id = divider
                    backgroundColor = ContextCompat.getColor(context, R.color.colorPrimary)
                }.lparams(width = matchParent, height = dip(2)) {
                    leftMargin = dip(5)
                    rightMargin = dip(5)
                }

                constraintLayout {
                    topPadding = dip(5)
                    bottomPadding = dip(5)

                    textView {
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        id = tvHome
                    }.lparams {
                        marginStart = dip(8)
                        startToStart = ConstraintSet.PARENT_ID
                    }

                    textView {
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        id = tvAway
                    }.lparams {
                        marginEnd = dip(8)
                        endToEnd = ConstraintSet.PARENT_ID
                    }

                    textView {
                        text = resources.getString(R.string.vs)
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        id = tvVersus
                    }.lparams {
                        marginEnd = dip(8)
                        marginStart = dip(8)
                        startToStart = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID
                    }

                    textView {
                        setTypeface(typeface, Typeface.BOLD)
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        id = tvHomeScore
                    }.lparams {
                        marginEnd = dip(8)
                        endToStart = tvVersus
                    }

                    textView {
                        setTypeface(typeface, Typeface.BOLD)
                        textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        id = tvAwayScore
                    }.lparams {
                        marginStart = dip(8)
                        startToEnd = tvVersus
                    }
                }.lparams(width = matchParent)
            }.lparams(width = matchParent, height = wrapContent) {
                bottomMargin = dip(16)
            }
        }
    }
}