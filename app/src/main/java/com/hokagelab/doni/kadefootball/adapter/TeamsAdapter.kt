package com.hokagelab.doni.kadefootball.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.model.Teams
import com.hokagelab.doni.kadefootball.ui.TeamsUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class TeamsAdapter(private var teamList: List<Teams>, private var listener: (Teams) -> Unit) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamsUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(this.teamList[position], listener)
    }

    override fun getItemCount(): Int = this.teamList.size

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeamName: TextView = itemView.findViewById(TeamsUI.tvTeamName)
        val ivTeamLogo: ImageView = itemView.findViewById(TeamsUI.ivTeamLogo)

        fun bindItem(items: Teams, listener: (Teams) -> Unit) {
            tvTeamName.text = items.teamName
            Picasso.get().load(items.teamBadge)
                .error(R.drawable.ic_launcher_background).into(ivTeamLogo)
            itemView.setOnClickListener { listener(items) }
        }
    }

}