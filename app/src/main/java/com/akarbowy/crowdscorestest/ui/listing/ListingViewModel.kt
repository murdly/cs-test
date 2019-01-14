package com.akarbowy.crowdscorestest.ui.listing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.data.matches.Competition
import com.akarbowy.crowdscorestest.data.matches.MatchesRepository
import com.akarbowy.crowdscorestest.extensions.scheduleOn
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class ListingViewModel @Inject constructor(
    private val matchesRepository: MatchesRepository
) : ViewModel() {

    val state: LiveData<ListingAction>
        get() {
            if (!::_state.isInitialized) {
                _state = MutableLiveData()
                _state.value = ListingAction.ShowLoading
            }
            return _state
        }

    private lateinit var _state: MutableLiveData<ListingAction>

    var day: Day? = null
        set(value) {
            field = value
            loadMatches(value)
        }

    private fun loadMatches(day: Day?) {
        day?.let {
            matchesRepository.loadMatches(day)
                .scheduleOn()
                .subscribeBy(
                    onSuccess = { data -> onMatchesLoaded(data) },
                    onError = { error -> onMatchesLoadingError(error) }
                )
        }
    }

    private fun onMatchesLoaded(matches: List<Competition>) {
        Timber.i("Loaded matches for ${day?.name}")

        _state.value = ListingAction.ShowMatches(matches)
    }

    private fun onMatchesLoadingError(error: Throwable) {
        Timber.e(error)

        _state.value = ListingAction.ShowError
    }

    sealed class ListingAction {
        object ShowLoading : ListingAction()
        class ShowMatches(val items: List<Competition>) : ListingAction()
        object ShowError : ListingAction()
    }
}
