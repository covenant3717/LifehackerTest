package com.covenant.lifehackertest.mvp

import com.covenant.lifehackertest.model.Post


/**
 * Created by Evgeny Kuksov 02.03.2020
 */

interface MainView {

    fun showProgress(show: Boolean)

    fun showError(error: String)

}