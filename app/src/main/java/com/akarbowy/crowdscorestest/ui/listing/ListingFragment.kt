package com.akarbowy.crowdscorestest.ui.listing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akarbowy.crowdscorestest.R
import com.akarbowy.crowdscorestest.data.days.Day
import kotlinx.android.synthetic.main.listing_fragment.*


class ListingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val day = arguments?.getSerializable(EXTRA_DAY) as Day

        textten.text = day.name

    }

    companion object {
        private val EXTRA_DAY = "extra_day"

        fun newInstance(day: Day) = ListingFragment().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_DAY, day)
            }
        }
    }

}
