package com.akarbowy.crowdscorestest.data.days

import dagger.Reusable

@Reusable
class DayProviderImpl : DayProvider {

    override fun getDays(): List<Day> {
        return listOf(Day.Yesterday, Day.Today, Day.Tomorrow)
    }
}