package com.covenant.lifehackertest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.covenant.lifehackertest.R
import com.covenant.lifehackertest.mvp.MainPresenter
import com.covenant.lifehackertest.mvp.MainView
import com.covenant.lifehackertest.ui.adapter.RVAdapter
import com.covenant.lifehackertest.util.invisible
import com.covenant.lifehackertest.util.toast
import com.covenant.lifehackertest.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { MainPresenter(this) }
    private val rvAdapter by lazy { RVAdapter() }

    //==============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMain()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            main_pb.visible()
            main_rv.invisible()
        } else {
            main_pb.invisible()
            main_rv.visible()
        }
    }

    override fun showError(error: String) {
        toast(error)
        main_pb.invisible()
    }

    //==============================================================================================

    private fun initMain() {
        initRvPosts()
        getPosts()
    }

    private fun getPosts() {
        presenter.postsLD.observe(this, Observer {
            rvAdapter.setData(it)
        })
    }

    private fun initRvPosts() {
        main_rv.adapter = rvAdapter
    }

}
