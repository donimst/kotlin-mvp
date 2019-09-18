package com.hokagelab.doni.kadefootball.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hokagelab.doni.kadefootball.R
import com.hokagelab.doni.kadefootball.model.Players
import com.hokagelab.doni.kadefootball.ui.PlayersUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class PlayersAdapter(private var playerList: List<Players>, private var listener: (Players) -> Unit) :
    RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayersUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(this.playerList[position], listener)
    }

    override fun getItemCount(): Int = this.playerList.size

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlayerName: TextView = itemView.findViewById(PlayersUI.tvPlayerName)
        val tvPlayerPosition: TextView = itemView.findViewById(PlayersUI.tvPlayerPosition)
        val ivPlayerAvatar: ImageView = itemView.findViewById(PlayersUI.ivPlayerAvatar)

        fun bindItem(items: Players, listener: (Players) -> Unit) {

            tvPlayerName.text = items.playerName
            tvPlayerPosition.text = items.playerPosition
            when (items.playerAvatar != null) {
                true -> Picasso.get().load(items.playerAvatar)
                    .error(R.drawable.ic_avatar_128).into(ivPlayerAvatar)
                false -> Picasso.get().load(R.drawable.ic_avatar_128).into(ivPlayerAvatar)
            }
            itemView.setOnClickListener { listener(items) }
        }
    }

}