package com.afrosin.popularlib.presenter

import com.afrosin.popularlib.model.CountersModel
import com.afrosin.popularlib.view.MainView

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(btnIndex: Int) {
        val nextValue = model.next(btnIndex)
        view.setButtonText(btnIndex, nextValue.toString())
    }
}