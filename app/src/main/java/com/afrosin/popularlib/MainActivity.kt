package com.afrosin.popularlib

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.afrosin.popularlib.databinding.ActivityMainBinding
import com.afrosin.popularlib.presenter.MainPresenter
import com.afrosin.popularlib.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val presenter = MainPresenter(this)
    private lateinit var btnList: MutableList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        initBtnList()
    }

    private fun initBtnList() {
        btnList = mutableListOf(vb.btnCounter1, vb.btnCounter2, vb.btnCounter3)

        btnList.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                presenter.counterClick(index)
            }
        }
    }

    override fun setButtonText(btnIndex: Int, text: String) {
        btnList[btnIndex].text = text
    }

}