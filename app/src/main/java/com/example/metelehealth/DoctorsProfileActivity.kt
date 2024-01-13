package com.example.metelehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.metelehealth.R
import com.example.metelehealth.adapters.DoctorsProfileAdapter
import com.example.metelehealth.model.DoctorsProfile

class DoctorsProfileActivity : AppCompatActivity() {
    private lateinit var imageId: Array<Int>
    private lateinit var doctorName : Array<String>
    private lateinit var doctorQuote : Array<String>
    private lateinit var doctorArrayList : ArrayList<DoctorsProfile>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctos_profile)

        imageId = arrayOf(
            R.drawable.doctor1,
            R.drawable.doctor2,
            R.drawable.doctor4,
            R.drawable.doctor5,
            R.drawable.group1,
            R.drawable.group3,
            R.drawable.doctor5,
            R.drawable.group1,
            R.drawable.group3
        )
        doctorName = arrayOf(
                "Dr. Timz Owen",
                "Dr. Allan Kipkosgei",
                "DR. Marini Clement",
                "Dr. Julia",
                "Dr. Esther",
                "Dr. Irene",
                "Dr. Kamau",
                "Dr. Kipkebut",
                "Dr.Masese",
                "Dr.Laura"
        )
        doctorQuote = arrayOf(
            "Health is welath",
            "Health is everything",
            "We cure God heals",
            "Therapy solves it",
            "Health eating is all",
            "We are family medicine",
            "Let's talk about us",
            "Health eating is all",
            "We are family medicine",
            "Let's talk about us"
            )

        recyclerView = findViewById(R.id.recycler_profile_doctors)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()

        doctorArrayList = arrayListOf<DoctorsProfile>()

        getDoctorsProfiles()

    }

    private fun getDoctorsProfiles(){
        for (i in imageId.indices){
            val profiles = DoctorsProfile(imageId[i],doctorName[i],doctorQuote[i])
            doctorArrayList.add(profiles)
        }
        val adapter = DoctorsProfileAdapter(doctorArrayList)
        recyclerView.adapter =  adapter
        adapter.setonItemClickListener(object : DoctorsProfileAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@DoctorsProfileActivity, "clicked $position", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@DoctorsProfileActivity,IDoctorActivity::class.java)
                intent.putExtra("name",doctorArrayList[position].doctorName)
                intent.putExtra("quote",doctorArrayList[position].quote)
                intent.putExtra("profile",doctorArrayList[position].imageProfile)
                startActivity(intent)
            }

        })

    }


}