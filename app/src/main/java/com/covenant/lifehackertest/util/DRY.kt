package com.covenant.lifehackertest.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat


/**
 * Created by Evgeny Kuksov 02.03.2020
 */

// DRY - Don`t repeat yourself

fun mlg(str: String){
    Log.d("ml", str)
}

fun Context.toast(str: String){
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
}

fun Toolbar.customInit(ctx: Context, background: Int, title: String, titleColor: Int, setMainIcon: Boolean, mainIconDrawable: Int = 0, mainIconColor: Int): Toolbar {
    (ctx as AppCompatActivity).setSupportActionBar(this)

    // бекграунд тулбара и прозрачность на 0
    this.setBackgroundColor(background)
    this.background.alpha = 255

    // заголовок и его цвет
    (ctx.supportActionBar)?.title = title
    this.setTitleTextColor(titleColor)

    if (setMainIcon) {
        // показать иконку назад/гамбургер
        (ctx.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        (ctx.supportActionBar)?.setDisplayShowHomeEnabled(true)

        // программно задаю цвет иконке
        var drawable = ResourcesCompat.getDrawable(ctx.resources, mainIconDrawable, null)
        drawable = drawable?.let { DrawableCompat.wrap(it) }
        drawable?.let { DrawableCompat.setTint(it, mainIconColor) }
        (ctx.supportActionBar)?.setHomeAsUpIndicator(drawable)
    }

    return this
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}