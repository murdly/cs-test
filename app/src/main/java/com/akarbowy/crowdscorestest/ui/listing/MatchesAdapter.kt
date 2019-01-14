package com.akarbowy.crowdscorestest.ui.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akarbowy.crowdscorestest.R
import com.akarbowy.crowdscorestest.data.matches.Match


class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    var data: List<Match> = emptyList()

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(data, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_match, parent, false)
        return MatchViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var homeView: TextView = super.itemView.findViewById(R.id.home)
        private var scoreView: TextView = super.itemView.findViewById(R.id.score)
        private var awayView: TextView = super.itemView.findViewById(R.id.away)

        fun bind(matches: List<Match>, position: Int) {

            val match = matches[position]

            homeView.text = match.home?.name
            scoreView.text = "${match.homeScore} - ${match.awayScore}" //todo future
            awayView.text = match.away?.name
        }

    }
}