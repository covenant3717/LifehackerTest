package com.covenant.lifehackertest.ui.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.covenant.lifehackertest.R
import com.covenant.lifehackertest.model.Post
import com.covenant.lifehackertest.util.OBJECT
import com.covenant.lifehackertest.util.customInit
import com.covenant.lifehackertest.util.mlg
import kotlinx.android.synthetic.main.activity_post.*


class PostActivity : AppCompatActivity() {

    private lateinit var data: Post

    //==============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        initMain()
    }

    //==============================================================================================

    private fun initMain() {
        getData()
        initToolbar()
        initWebView()
    }

    private fun getData() {
        data = intent.getSerializableExtra(OBJECT) as Post
        mlg(data.toString())
    }

    private fun initToolbar() {
        post_tb.customInit(
            this,
            resources.getColor(R.color.colorPrimary),
            data.title.rendered,
            resources.getColor(android.R.color.white),
            true,
            R.drawable.ic_arrow_back,
            resources.getColor(android.R.color.white)
        ).setNavigationOnClickListener {
            // icon clickListener

            finish()
        }
    }

    private fun initWebView() {
        post_wv.webViewClient = WebViewClient()
        post_wv.settings.javaScriptEnabled = true
        post_wv.loadUrl(data.link)

        post_wv.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100 && post_pb.visibility == ProgressBar.GONE) {
                    post_pb.visibility = ProgressBar.VISIBLE
                }

                post_pb.progress = progress
                if (progress == 100) {
                    post_pb.setVisibility(ProgressBar.GONE)
                }
            }
        }
    }


}


