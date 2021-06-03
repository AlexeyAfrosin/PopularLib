package com.afrosin.popularlib

import android.os.Bundle
import com.afrosin.popularlib.databinding.ActivityMainBinding
import com.afrosin.popularlib.view.AndroidScreens
import com.afrosin.popularlib.view.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.container)
    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
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
