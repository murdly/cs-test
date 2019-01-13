package com.akarbowy.crowdscorestest

import android.app.Application
import com.akarbowy.crowdscorestest.injection.AppComponent
import com.akarbowy.crowdscorestest.injection.DaggerAppComponent
import com.akarbowy.crowdscorestest.injection.DaggerComponentProvider
import timber.log.Timber


class App : Application(), DaggerComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}