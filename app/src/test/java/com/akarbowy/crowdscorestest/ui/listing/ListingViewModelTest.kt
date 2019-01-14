package com.akarbowy.crowdscorestest.ui.listing

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.matches.MatchesRepository
import com.akarbowy.crowdscorestest.data.network.MatchesResponse
import com.akarbowy.crowdscorestest.helpers.InstantRxSchedulerRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.reset
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class ListingViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var instantRxSchedulerRule = InstantRxSchedulerRule()

    @Captor
    private val stateArgumentCaptor = ArgumentCaptor.forClass(ListingViewModel.ListingAction::class.java)

    private val stateObserver: Observer<ListingViewModel.ListingAction> = mock()

    private val mockMatchesRepository: MatchesRepository = mock()

    private lateinit var viewModel: ListingViewModel

    @Before
    fun setUp() {
        viewModel = ListingViewModel(mockMatchesRepository)

        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun showMatchesWhenSuccessfulResponse() {
        reset(stateObserver)

        `when`(mockMatchesRepository.loadMatches(DAY)) doReturn Single.just(MATCHES_SUCCESS)

        viewModel.day = DAY

        verify(mockMatchesRepository).loadMatches(DAY)
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is ListingViewModel.ListingAction.ShowMatches)
    }

    @Test
    fun showErrorWhenNoSuccessfulResponse() {
        reset(stateObserver)

        `when`(mockMatchesRepository.loadMatches(DAY)) doReturn Single.error(MATCHES_ERROR)

        viewModel.day = DAY

        verify(mockMatchesRepository).loadMatches(DAY)
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is ListingViewModel.ListingAction.ShowError)
    }

    companion object {
        private val DAY = Day.Today

        private val MATCHES_SUCCESS = MatchesResponse()

        private val MATCHES_ERROR = Throwable()
    }


}

