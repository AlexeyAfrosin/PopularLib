package com.afrosin.popularlib

import android.os.Bundle
import com.afrosin.popularlib.presenter.abstr.AbstractActivity
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AbstractActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var androidScreens: AndroidScreens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.replaceScreen(androidScreens.users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}
