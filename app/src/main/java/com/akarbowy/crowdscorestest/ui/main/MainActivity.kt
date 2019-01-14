package com.akarbowy.crowdscorestest.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.akarbowy.crowdscorestest.R
import com.akarbowy.crowdscorestest.data.days.Day
import com.akarbowy.crowdscorestest.injection.getViewModel
import com.akarbowy.crowdscorestest.injection.injector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        getViewModel { injector.mainViewModel }
    }

    private val dayAdapter = DayAdapter(this, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_menu)

        pager.adapter = dayAdapter

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_matches -> {
                    toolbar.post { title = getString(R.string.title_matches) }

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        navigation.selectedItemId = R.id.navigation_matches

        viewModel.state.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(mainAction: MainViewModel.MainAction?) {
        when (mainAction) {
            is MainViewModel.MainAction.ShowTabs -> setDaysData(mainAction)
        }
    }

    private fun setDaysData(mainAction: MainViewModel.MainAction.ShowTabs) {
        dayAdapter.apply {
            days = mainAction.items
            notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_today -> {
                selectTodayTab()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun selectTodayTab() {
        val todayPosition = dayAdapter.days.indexOf(Day.Today)
        pager.setCurrentItem(todayPosition, true)
    }
}
