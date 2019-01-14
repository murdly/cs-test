package com.akarbowy.crowdscorestest.ui.listing

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.akarbowy.crowdscorestest.data.matches.Match


object MatchesBinder {

    fun setupList(list: RecyclerView) {
        MatchesAdapter()
            .also { list.adapter = it }

        val linearLayoutManager = LinearLayoutManager(list.context, LinearLayoutManager.VERTICAL, false)

        list.apply {
            setHasFixedSize(true)

            layoutManager = linearLayoutManager
        }
    }

    fun setData(list: RecyclerView, matches: List<Match>) {

        (list.adapter as MatchesAdapter).apply {
            data = matches
            notifyDataSetChanged()
        }
    }

}