package com.example.metelehealth

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker

import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.metelehealth.MainActivity
import com.example.metelehealth.R
import com.example.metelehealth.data.GroupMeeting
import com.example.metelehealth.databinding.ActivityBookAppointmentBinding
import com.example.metelehealth.model.BookAppointment
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookAppointmentBinding
    private lateinit var database : DatabaseReference

    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val today = Calendar.getInstance()  //get the date to pick
        val yearBooked = today.get(Calendar.YEAR)
        val monthBooked = today.get(Calendar.MONTH)
        val dayBooked = today.get(Calendar.DAY_OF_MONTH)
        binding.etAppointmentDate.setOnClickListener{
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.etAppointmentDate.text ="" + dayOfMonth + "/" + (month + 1) + "/" + year
            }, yearBooked,monthBooked,dayBooked)
            datePickerDialog.show()
        }

        binding.btnBookAppointment.setOnClickListener {

            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Booking Appointment.....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val fullName = binding.etAppointmentNames.text.toString()
            val reason = binding.etAppointmentReason.text.toString()
            val phone = binding.etAppointmentNumber.text.toString()
            val date = binding.etAppointmentDate.text.toString()

            val zoomMeetingDate = GroupMeeting(fullName, reason, null, date)

            // Store Zoom meeting details in the "zoomMeetings" node
            val zoomMeetingsRef = FirebaseDatabase.getInstance().getReference("zoomMeetings")
            val newZoomMeetingRef = zoomMeetingsRef.push()
            newZoomMeetingRef.setValue(zoomMeetingDate).addOnSuccessListener {
                database = FirebaseDatabase.getInstance().getReference("Appointments")

                val bookAppointment = BookAppointment(fullName,reason,date,phone)

                database.child(fullName).setValue(bookAppointment).addOnSuccessListener {

                    binding.etAppointmentNames.text.clear()
                    binding.etAppointmentReason.text.clear()
                    binding.etAppointmentNumber.text.clear()
                    Toast.makeText(this,"Session book successfully....", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()

                }.addOnFailureListener {
                    Toast.makeText(this,"Book again....Failed", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()

                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to book Zoom meeting.", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }


        }

    }

    // Menu inflater
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_profile -> startActivity(Intent(this, DoctorsProfileActivity::class.java))
            R.id.menu_home -> startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}