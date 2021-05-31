package com.afrosin.popularlib.presenter

import com.afrosin.popularlib.model.CountersModel
import com.afrosin.popularlib.view.MainView

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel
) {
    fun btnIncCounter1() {
        val nextValue = model.next(0)
        view.setButton1Text(nextValue.toString())
    }

    fun btnIncCounter2() {
        val nextValue = model.next(1)
        view.setButton2Text(nextValue.toString())
    }

    fun btnIncCounter3() {
        val nextValue = model.next(2)
        view.setButton3Text(nextValue.toString())
    }
}