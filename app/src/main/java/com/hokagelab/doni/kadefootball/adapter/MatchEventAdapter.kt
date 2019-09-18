package com.hokagelab.doni.kadefootball.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hokagelab.doni.kadefootball.model.MatchEvent
import com.hokagelab.doni.kadefootball.ui.MatchEventUI
import com.hokagelab.doni.kadefootball.utils.DateConverter
import org.jetbrains.anko.AnkoContext

class MatchEventAdapter(private var eventList: List<MatchEvent>, private var listener: (MatchEvent) -> Unit) :
    RecyclerView.Adapter<MatchEventAdapter.MatchEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchEventViewHolder {
        return MatchEventViewHolder(MatchEventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: MatchEventViewHolder, position: Int) {
        holder.bindItem(this.eventList[position], listener)
    }

    override fun getItemCount(): Int = this.eventList.size

    inner class MatchEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRound: TextView = itemView.findViewById(MatchEventUI.tvRound)
        val dtTime: TextView = itemView.findViewById(MatchEventUI.dtTime)
        val tvHome: TextView = itemView.findViewById(MatchEventUI.tvHome)
        val tvHomeScore: TextView = itemView.findViewById(MatchEventUI.tvHomeScore)
        val tvAway: TextView = itemView.findViewById(MatchEventUI.tvAway)
        val tvAwayScore: TextView = itemView.findViewById(MatchEventUI.tvAwayScore)

        fun bindItem(items: MatchEvent, listener: (MatchEvent) -> Unit) {
            tvRound.text = items.league + " - Round " + items.round.toString()
            if (items.strDate != null && items.strTime != null) {
                dtTime.text = DateConverter().convertDate(items.strDate + " " + items.strTime)
            } else {
                dtTime.text = "-"
            }
            tvHome.text = items.homeTeam
            tvHomeScore.text = items.homeScore?.toString() ?: "-"
            tvAway.text = items.awayTeam
            tvAwayScore.text = items.awayScore?.toString() ?: "-"
            itemView.setOnClickListener { listener(items) }
        }
    }

}