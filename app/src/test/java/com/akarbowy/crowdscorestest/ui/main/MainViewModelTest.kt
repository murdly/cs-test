package com.akarbowy.crowdscorestest.ui.main

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.akarbowy.crowdscorestest.data.days.DayProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.verify


class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    private val stateArgumentCaptor = ArgumentCaptor.forClass(MainViewModel.MainAction::class.java)

    private val stateObserver: Observer<MainViewModel.MainAction> = mock()

    private val mockDayProvider = mock<DayProvider> {
        on { getDays() } doReturn emptyList()
    }

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(mockDayProvider)

        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun showTabsUponInitialization() {
        verify(mockDayProvider).getDays()
        verify(stateObserver).onChanged(stateArgumentCaptor.capture())
        assert(stateArgumentCaptor.value is MainViewModel.MainAction.ShowTabs)
    }
}