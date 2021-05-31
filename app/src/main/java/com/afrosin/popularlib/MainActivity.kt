package com.afrosin.popularlib

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.afrosin.popularlib.databinding.ActivityMainBinding
import com.afrosin.popularlib.model.CountersModel
import com.afrosin.popularlib.presenter.MainPresenter
import com.afrosin.popularlib.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val presenter = MainPresenter(this, CountersModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        initBtnList()
    }

    override fun setButton1Text(text: String) {
        setButtonText(vb.btnCounter1, text)
    }

    override fun setButton2Text(text: String) {
        setButtonText(vb.btnCounter2, text)
    }

    override fun setButton3Text(text: String) {
        setButtonText(vb.btnCounter3, text)
    }

    private fun initBtnList() {
        vb.btnCounter1.setOnClickListener { presenter.btnIncCounter1() }
        vb.btnCounter2.setOnClickListener { presenter.btnIncCounter2() }
        vb.btnCounter3.setOnClickListener { presenter.btnIncCounter3() }

    }

    private fun setButtonText(btn: Button, text: String) {
        btn.text = text
    }

}