package com.akarbowy.crowdscorestest.ui.listing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akarbowy.crowdscorestest.R
import kotlinx.android.synthetic.main.listing_fragment.*

enum class DAY(val textResId: Int) {
    Yesterday(R.string.day_yesterday),
    Today(R.string.day_today),
    Tomorrow(R.string.day_tomorrow)
}

class ListingFragment : Fragment() {

    companion object {
        private val EXTRA_DAY = "extra_day"

        fun newInstance(day: DAY) = ListingFragment().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_DAY, day)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val day = arguments?.getSerializable(EXTRA_DAY) as DAY

        textten.text = day.name
    }

}
