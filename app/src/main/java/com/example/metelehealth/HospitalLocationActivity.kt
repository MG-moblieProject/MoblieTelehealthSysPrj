package com.example.metelehealth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.metelehealth.R

class HospitalLocationActivity : AppCompatActivity() {

    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_location)


        initView()

        Handler().postDelayed({
            progressDialog.dismiss()
        },6000)

    }

    //create a function to initialize the google maps API
    @SuppressLint("SetJavaScriptEnabled")
    private fun initView(){
        //find the ID to the map
        val mapview : WebView = findViewById(R.id.wv_mapview)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Getting nearby hospitals.....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        mapview.webViewClient= object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url : String
            ): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        mapview.settings.javaScriptEnabled = true
        mapview.loadUrl("https://bit.ly/3OlRNlP")
        mapview.settings.supportZoom()
    }
}