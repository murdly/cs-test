package com.akarbowy.crowdscorestest.ui.listing

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akarbowy.crowdscorestest.R
import com.akarbowy.crowdscorestest.data.matches.Competition


class CompetitionsAdapter : RecyclerView.Adapter<CompetitionsAdapter.CompetitionViewHolder>() {

    var data: List<Competition> = emptyList()

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.bind(data, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_competition, parent, false)
        return CompetitionViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    class CompetitionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var competitionView: TextView = super.itemView.findViewById(R.id.competition)
        private var roundView: TextView = super.itemView.findViewById(R.id.round)
        private var matchesView: RecyclerView = super.itemView.findViewById(R.id.matches)

        fun bind(competitions: List<Competition>, position: Int) {

            val competition = competitions[position]

            competitionView.text = competition.name

            if (competition.round?.name != null) {
                roundView.visibility = View.VISIBLE
                roundView.text = competition.round.name
            } else {
                roundView.visibility = View.GONE
            }

            competition.round?.matches?.let {
                matchesView.visibility = View.VISIBLE
                MatchesBinder.setupList(matchesView)
                MatchesBinder.setData(matchesView, it)
            } ?: run { matchesView.visibility = View.GONE }

        }

    }
}