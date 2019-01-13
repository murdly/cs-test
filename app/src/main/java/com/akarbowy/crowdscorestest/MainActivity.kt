package com.akarbowy.crowdscorestest

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.akarbowy.crowdscorestest.injection.getViewModel
import com.akarbowy.crowdscorestest.injection.injector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        getViewModel { injector.mainViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.ic_menu)

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

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
