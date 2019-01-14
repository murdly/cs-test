package com.akarbowy.crowdscorestest.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.ui.listing.ListingFragment


class DayAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    var days = emptyList<Day>()

    override fun getItem(position: Int): Fragment {
        return ListingFragment.newInstance(days[position])
    }

    override fun getCount(): Int {
        return days.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(days[position].textResId)
    }

}