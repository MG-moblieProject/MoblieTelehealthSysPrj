package com.example.metelehealth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.metelehealth.databinding.ActivityLoginBinding
import com.example.metelehealth.EmergencyActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // make a direct call to dial intents
        binding.btnCallEmergency.setOnClickListener {
            startActivity(Intent(this, EmergencyActivity::class.java))
        }
        //navigate back to sign up activity
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait.....")
        progressDialog.setCancelable(false)

        Handler().postDelayed({
            progressDialog.dismiss()
        },4000)

        //Authenticate user login
        binding.btnLogin.setOnClickListener {

            val userEmail = binding.etSigninEmail.text.toString()
            val userPassword = binding.etPasswordLogin.text.toString()

            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()){
                progressDialog.show()

                firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        progressDialog.dismiss()
                    }else{
                        Toast.makeText(this, "Please create account..", Toast.LENGTH_LONG).show()
                        progressDialog.dismiss()
                    }
                }
            }else{
                if (userEmail.isEmpty()){
                    binding.etSigninEmail.error = "Email cannot be blank"
                }else if (userPassword.isEmpty()){
                    binding.etPasswordLogin.error = "Enter password"
                }
            }

        }
    }
}