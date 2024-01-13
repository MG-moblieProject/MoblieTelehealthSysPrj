package com.example.metelehealth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.metelehealth.databinding.ActivitySignUpBinding

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
            val password = binding.etUsersignupPassword.text.toString()
            val confirmPswd = binding.etSignupConfirmPswd.text.toString()

            //check if the fields are not empty
            if (userEmail.isNotEmpty() && password.isNotEmpty() && confirmPswd.isNotEmpty()) {
                progressDialog.show()
                    if (password == confirmPswd) {
                        firebaseAuth.createUserWithEmailAndPassword(userEmail, password)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    progressDialog.dismiss()
                                    // sign up user on success
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
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
                    binding.etSignupUserEmail.error = "Enter email"
                }else if(password.isEmpty()){
                    binding.etUsersignupPassword.error = "Password cannot be empty"
                }else if(confirmPswd.isEmpty()){
                    binding.etSignupConfirmPswd.error = "Cannot be empty"
                }
            }

        }

    }
}