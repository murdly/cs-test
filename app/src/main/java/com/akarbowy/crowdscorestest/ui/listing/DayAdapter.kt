package com.akarbowy.crowdscorestest.ui.listing

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class DayAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    var days = emptyList<DAY>()

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