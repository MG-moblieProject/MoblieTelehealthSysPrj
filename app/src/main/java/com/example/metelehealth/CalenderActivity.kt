package com.example.metelehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.metelehealth.databinding.ActivityCalenderBinding


class CalenderActivity : AppCompatActivity() {

    private lateinit var calenderBinding: ActivityCalenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calenderBinding = ActivityCalenderBinding.inflate(layoutInflater)
        setContentView(calenderBinding.root)

        calenderBinding.btnBookAppointment.setOnClickListener {
            startActivity(Intent(this, BookAppointmentActivity::class.java))
        }

        calenderBinding.btnBookTherapy.setOnClickListener {
            startActivity(Intent(this, DoctorsProfileActivity::class.java))
        }

        calenderBinding.btnCheckPrescription.setOnClickListener {
            startActivity(Intent(this,PrescriptionActivity::class.java))
        }

    }
}