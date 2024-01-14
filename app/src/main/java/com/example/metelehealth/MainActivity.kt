package com.example.metelehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.example.metelehealth.databinding.ActivityMainBinding
import com.example.metelehealth.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.cardEmergency.setOnClickListener {
             startActivity(Intent(this,EmergencyActivity::class.java))
         }
         binding.cardCalendar.setOnClickListener {
             startActivity(Intent(this,CalenderActivity::class.java))
         }
         binding.cardCovidSupport.setOnClickListener {
             startActivity(Intent(this,CovidSupportActivity::class.java))
         }
         binding.cardIDoctor.setOnClickListener {
             startActivity(Intent(this,DoctorsProfileActivity::class.java))
         }
         binding.cardLocateMedication.setOnClickListener {
             startActivity(Intent(this,HospitalLocationActivity::class.java))
         }
         binding.cardGroupTherapy.setOnClickListener {
             startActivity(Intent(this,GroupTherapyActivity::class.java))
         }

    }
}