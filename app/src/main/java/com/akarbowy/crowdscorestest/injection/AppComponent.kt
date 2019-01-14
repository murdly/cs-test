package com.akarbowy.crowdscorestest.injection

import android.content.Context
import com.akarbowy.crowdscorestest.data.injection.DataModule
import com.akarbowy.crowdscorestest.data.injection.NetworkModule
import com.akarbowy.crowdscorestest.ui.listing.ListingViewModel
import com.akarbowy.crowdscorestest.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    val mainViewModel: MainViewModel

    val listingViewModel: ListingViewModel

}