package com.afrosin.popularlib

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions

@Suppress("IMPLICIT_CAST_TO_ANY")
fun ImageView.setCircleImageFromUrl(url: String, placeholder: Int = 0) {
    val glideUrl = if (url.isEmpty()) placeholder else GlideUrl(url)
    Glide.with((context))
        .load(glideUrl)
        .placeholder(placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }