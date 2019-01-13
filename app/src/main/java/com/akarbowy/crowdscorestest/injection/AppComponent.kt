package com.akarbowy.crowdscorestest.injection

import android.content.Context
import com.akarbowy.crowdscorestest.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    val mainViewModel: MainViewModel

}