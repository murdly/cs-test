package com.akarbowy.crowdscorestest.ui.listing

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.akarbowy.crowdscorestest.data.matches.Competition


object CompetitionsBinder {

    fun setupList(list: RecyclerView) {
        CompetitionsAdapter()
            .also { list.adapter = it }

        val linearLayoutManager = LinearLayoutManager(list.context, LinearLayoutManager.VERTICAL, false)

        list.apply {
            setHasFixedSize(true)

            layoutManager = linearLayoutManager
        }
    }

    fun setData(list: RecyclerView, competitions: List<Competition>) {

        (list.adapter as CompetitionsAdapter).apply {
            data = competitions
            notifyDataSetChanged()
        }
    }

}