package com.example.metelehealth

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.metelehealth.databinding.ActivityPrescriptionBinding
import com.google.firebase.auth.FirebaseAuth

class PrescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrescriptionBinding
    private lateinit var db: DatabaseReference
    private lateinit var progressDialog: ProgressDialog

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetPrescription.setOnClickListener {

            val userName: String = binding.etSearchUserName.text.toString()

            if (userName.isNotEmpty()) {

                progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Checking your prescription.....")
                progressDialog.setCancelable(false)
                progressDialog.show()

                sendPrescription(userName)

                Handler().postDelayed({
                    progressDialog.dismiss()
                }, 4000)

            } else {
                Toast.makeText(this, "Please enter Name....", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun sendPrescription(userName: String) {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            db = FirebaseDatabase.getInstance().getReference("users")
            db.child(uid).get().addOnSuccessListener {

                if (it.exists()) {

                    val fullName = it.child("fullName").value
                    val administered = it.child("administered").value
                    val medName = it.child("medname").value

                    Toast.makeText(this, "Prescription Done fetching....", Toast.LENGTH_SHORT)
                        .show()

                    binding.etSearchUserName.text.clear()
                    binding.tvUserNamePrescription.text = fullName.toString()
                    binding.etNumAdministered.text = administered.toString()
                    binding.tvMedName.text = medName.toString()

                } else {
                    Toast.makeText(this, "User not found.....", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {

                Toast.makeText(this, "Contact doctors....", Toast.LENGTH_SHORT).show()
            }
        }
    }
}