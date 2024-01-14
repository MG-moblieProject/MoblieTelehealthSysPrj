package com.example.metelehealth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.metelehealth.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.example.metelehealth.databinding.ActivitySignUpBinding
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvMemberLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Creating account....")
        progressDialog.setCancelable(false)

        binding.btnCreateUserAccountf.setOnClickListener {

            val userEmail = binding.etSignupUserEmail.text.toString()
            val userAge = binding.etUserAge.text.toString()
            val userFullName = binding.etUserFullName.text.toString()
            val password = binding.etUsersignupPassword.text.toString()
            val confirmPswd = binding.etSignupConfirmPswd.text.toString()

            //check if the fields are not empty
            if (userEmail.isNotEmpty() && password.isNotEmpty() && confirmPswd.isNotEmpty()) {
                progressDialog.show()
                    if (password == confirmPswd) {
                        firebaseAuth.createUserWithEmailAndPassword(userEmail, password)
                            .addOnCompleteListener {authTask ->
                                if (authTask.isSuccessful) {
                                    val currentUser = firebaseAuth.currentUser
                                    val databaseInsert = FirebaseDatabase.getInstance().getReference("users/${currentUser?.uid}")

                                    val user = Users(email= userEmail,fullName = userFullName, age = userAge)

                                    databaseInsert.setValue(user)
                                        .addOnCompleteListener { databaseTask ->
                                            progressDialog.dismiss()
                                            if(databaseTask.isSuccessful){
                                                // sign up user on success
                                                val intent = Intent(this, LoginActivity::class.java)
                                                startActivity(intent)
                                            } else {
                                                Toast.makeText(this, "Error adding user to the database!",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    }

                                } else {
                                    progressDialog.dismiss()
                                    Toast.makeText(this, "Correct the Errors..!", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                    } else {
                        progressDialog.dismiss()
                        binding.etUsersignupPassword.error = "Password does not match!!"
                        Toast.makeText(this, "Correct the Errors!! ", Toast.LENGTH_LONG).show()

                    }
            }else{
                if(userEmail.isEmpty()){
                    binding.etSignupUserEmail.error = "Please enter your email"
                }else if(password.isEmpty()){
                    binding.etUsersignupPassword.error = "Password cannot be empty"
                }else if(confirmPswd.isEmpty()){
                    binding.etSignupConfirmPswd.error = "Please enter password confirmation"
                }else if (userFullName.isEmpty()){
                    binding.etUserFullName.error = "Please enter your full name"
                }
            }

        }

    }
}