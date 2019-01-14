package com.akarbowy.crowdscorestest.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.days.DayProvider
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val dayProvider: DayProvider
) : ViewModel() {

    val state: LiveData<MainAction>
        get() {
            if (!::_state.isInitialized) {
                _state = MutableLiveData()
                loadData()
            }
            return _state
        }

    private lateinit var _state: MutableLiveData<MainAction>

    private fun loadData() {
        _state.value = MainAction.ShowTabs(dayProvider.getDays())
    }

    sealed class MainAction {
        class ShowTabs(val items: List<Day>) : MainAction()
    }

}