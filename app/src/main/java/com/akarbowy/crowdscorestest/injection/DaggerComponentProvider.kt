package com.akarbowy.crowdscorestest.injection

import android.app.Activity


interface DaggerComponentProvider {

    val component: AppComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).component