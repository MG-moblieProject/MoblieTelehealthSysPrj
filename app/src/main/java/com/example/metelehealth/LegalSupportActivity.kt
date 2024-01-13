package com.example.metelehealth

import android.app.DatePickerDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.view.View
import com.example.metelehealth.databinding.ActivityLegalSupportBinding
import com.example.metelehealth.model.LegalCase
import java.time.Month
import java.util.*

class LegalSupportActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLegalSupportBinding
    private  lateinit var database : DatabaseReference

    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // use view binding to avoid boiler plate code collection
        binding = ActivityLegalSupportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val today = Calendar.getInstance()  //get the date to pick

        val year_reported = today.get(Calendar.YEAR)
        val month_reported = today.get(Calendar.MONTH)
        val day_reported = today.get(Calendar.DAY_OF_MONTH)

        binding.etDateOccurrence.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.etDateOccurrence.text =  "Date" +  dayOfMonth + "/" + (month + 1) + "/" + year
            }, year_reported,month_reported,day_reported)
            datePickerDialog.show()
        }

        binding.btnSubmitCase.setOnClickListener{

            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Reporting case.....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val hospName = binding.etHospName.text.toString()
            val caseType = binding.etTypeOfCase.text.toString()
            val dateOccurrence = binding.etDateOccurrence.text.toString()
            val involvedPerson = binding.etVictimInvolved.text.toString()
            val briefDesc = binding.etBriefDesc.text.toString()

            database = FirebaseDatabase.getInstance().getReference("LegalCases")

            val LegalCase = LegalCase(hospName,caseType,dateOccurrence,involvedPerson,briefDesc)

            database.child(hospName).setValue(LegalCase).addOnSuccessListener {

                binding.etHospName.text.clear()
                binding.etTypeOfCase.text.clear()

                binding.etVictimInvolved.text.clear()
                binding.etVictimInvolved.text.clear()
                binding.etBriefDesc.text.clear()

                Toast.makeText(this,"successfully reported....",Toast.LENGTH_LONG).show()


                Handler().postDelayed({
                    progressDialog.dismiss()
                },8000)

                progressDialog.dismiss()

            }.addOnFailureListener {

                Toast.makeText(this,"Failed. Try again..",Toast.LENGTH_LONG).show()
                progressDialog.dismiss()

            }


        }



    }

}