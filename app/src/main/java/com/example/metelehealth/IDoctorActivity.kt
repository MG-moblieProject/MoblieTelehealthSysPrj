package com.example.metelehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.metelehealth.R
import com.example.metelehealth.databinding.ActivityIdoctorBinding
import org.w3c.dom.Text


class IDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idoctor)


        val docProfile : ImageView = findViewById(R.id.iv_DocProfile_chat)
        val docName : TextView = findViewById(R.id.name_profile)
        val docQuote : TextView = findViewById(R.id.country_profile)

        val bundle : Bundle?= intent.extras
        val bundleName  = bundle!!.getString("name")
        val bundleImage = bundle.getInt("profile")
        val bundleQuote = bundle.getString("quote")

        docName.text = bundleName
        docProfile.setImageResource(bundleImage)
        docQuote.text = bundleQuote

        val btn : Button = findViewById(R.id.tbn_bookTherapy_doctor)
        btn.setOnClickListener{
            startActivity(Intent(this,BookAppointmentActivity::class.java))
            }
        }
}