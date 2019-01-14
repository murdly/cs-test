package com.akarbowy.crowdscorestest.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T) = T::class.java.let {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T) = T::class.java.let {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}