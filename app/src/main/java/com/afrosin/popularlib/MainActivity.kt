package com.afrosin.popularlib

import android.os.Bundle
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.router.replaceScreen(AndroidScreens().users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }


    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}
