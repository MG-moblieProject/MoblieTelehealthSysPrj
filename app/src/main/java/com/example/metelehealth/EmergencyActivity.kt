package com.example.metelehealth

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.metelehealth.MainActivity
import com.example.metelehealth.R
import com.example.metelehealth.databinding.ActivityEmergencyBinding
import com.example.metelehealth.adapters.EmergencyAdapter
import com.example.metelehealth.data.DataEmergency

class EmergencyActivity : AppCompatActivity(), EmergencyAdapter.onItemClickListener {

    private lateinit var binding: ActivityEmergencyBinding

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onClickRequestPermission(binding.recyclerEmergency)
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmergencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the recycler view
        val recyclerviewEmergency = findViewById<RecyclerView>(R.id.recycler_emergency)

        //get all the numbers in the emergency data package
        val dataNumbers = DataEmergency().loadCalls()

        // map all the data in the adapter
        recyclerviewEmergency.adapter = EmergencyAdapter(this, dataNumbers, this)
        recyclerviewEmergency.hasFixedSize()
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0702020202"))
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0703030303"))
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0704040404"))
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0705050505"))
                startActivity(intent)
            }
            4 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0706060606"))
                startActivity(intent)
            }
            5 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0707070707"))
                startActivity(intent)
            }
            6 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0708080808"))
                startActivity(intent)
            }
            7 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0709090909"))
                startActivity(intent)
            }
            8 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0710101010"))
                startActivity(intent)
            }
            9 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0711111111"))
                startActivity(intent)
            }
            10 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "071212121212"))
                startActivity(intent)
            }
            11 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07131313131313"))
                startActivity(intent)
            }
            12 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07141414141414"))
                startActivity(intent)
            }
            13 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07151515151515"))
                startActivity(intent)
            }
            14 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "071616161616"))
                startActivity(intent)
            }
            15 -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0717171717"))
                startActivity(intent)
            }
        }
    }

    private fun onClickRequestPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED -> {
                binding.recyclerEmergency.showSnackbar(
                    view,
                    getString(R.string.permission_granted),
                    Snackbar.LENGTH_INDEFINITE,
                    null
                ) {}
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CALL_PHONE
            ) -> {
                binding.recyclerEmergency.showSnackbar(
                    view,
                    getString(R.string.permission_required),
                    Snackbar.LENGTH_INDEFINITE,
                    getString(R.string.ok)
                ) {
                    requestPermissionLauncher.launch(
                        android.Manifest.permission.CALL_PHONE
                    )
                }
            }

            else -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.CALL_PHONE
                )
            }
        }
    }

    private fun View.showSnackbar(
        view: View,
        msg: String,
        length: Int,
        actionMessage: CharSequence?,
        action: (View) -> Unit
    ) {
        val snackbar = Snackbar.make(view, msg, length)
        if (actionMessage != null) {
            snackbar.setAction(actionMessage) {
                action(this)
            }.show()
        } else {
            snackbar.show()
        }
    }
}