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
                "Dr. Nguyễn Ngọc Bách",
                "Dr. Vương Ngọc Thiên Thanh",
                "DR. Ngọc Thành Đạt",
                "Dr. Vũ Thiên Ân",
                "Dr. Võ Thị Minh Tuyền",
                "Dr. Nguyễn Xuân Thúy Quỳnh",
                "Dr. Nguyễn Chấn Hùng",
                "Dr. Nguyễn Sào Trung",
                "Dr.Trần Văn Thiệp",
                "Dr.Vũ Văn Vũ"
        )
        doctorQuote = arrayOf(
            "Sức Khỏe Là Vàng",
            "Sức khỏe là tất cả",
            "Chúng tôi chữa bệnh, Chúa chữa lành",
            "Liệu trình giải quyết mọi thứ",
            "Ăn uống lành mạnh là chìa khóa cho mọi vấn đề",
            "Chúng tôi là thầy thuốc của mọi gia đình",
            "Hãy nói tôi nghe thêm về bạn",
            "Mang trong mình trọng trách solo tay đôi với tử thần",
            "Sức khỏe của các bạn là thứ chúng tôi bảo đảm",
            "Vì một Việt Nam khỏe mạnh"
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