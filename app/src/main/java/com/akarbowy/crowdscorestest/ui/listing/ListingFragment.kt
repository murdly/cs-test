package com.akarbowy.crowdscorestest.ui.listing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akarbowy.crowdscorestest.R
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.network.MatchesResponse
import com.akarbowy.crowdscorestest.injection.getViewModel
import com.akarbowy.crowdscorestest.injection.injector
import kotlinx.android.synthetic.main.listing_fragment.*


class ListingFragment : Fragment() {

    private val viewModel by lazy {
        activity!!.getViewModel { activity!!.injector.listingViewModel }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val day = arguments?.getSerializable(EXTRA_DAY) as Day

        viewModel.day = day
        viewModel.state.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(mainAction: ListingViewModel.ListingAction?) {
        when (mainAction) {
            is ListingViewModel.ListingAction.ShowLoading -> {
                showLoading()
            }
            is ListingViewModel.ListingAction.ShowMatches -> {
                showMatches(mainAction.items)
            }
            is ListingViewModel.ListingAction.ShowError -> {
                showError()
            }
        }
    }

    private fun showLoading() {
        indicator.visibility = View.VISIBLE
        list.visibility = View.GONE
        error.visibility = View.GONE
    }

    private fun showMatches(items: MatchesResponse) {
        list.visibility = View.VISIBLE
        indicator.visibility = View.GONE
        error.visibility = View.GONE
    }

    private fun showError() {
        error.visibility = View.VISIBLE
        list.visibility = View.GONE
        indicator.visibility = View.GONE
    }

    companion object {
        private const val EXTRA_DAY = "extra_day"

        fun newInstance(day: Day) = ListingFragment().apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_DAY, day)
            }
        }
    }

}
