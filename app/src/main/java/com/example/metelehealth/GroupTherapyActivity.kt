package com.example.metelehealth

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.example.metelehealth.adapters.GroupTherapyAdapter
import com.example.metelehealth.data.GroupMeeting

class GroupTherapyActivity : AppCompatActivity(), GroupTherapyAdapter.OnItemClickListener {

    private lateinit var dbRef : DatabaseReference
    private lateinit var groupRecyclerView : RecyclerView
    private lateinit var groupArrayList : ArrayList<GroupMeeting>
    private lateinit var progressDialog : ProgressDialog
    private lateinit var groupTherapyAdapter: GroupTherapyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_therapy)

        groupRecyclerView = findViewById(R.id.groupTherapyRecycler_view)
        groupRecyclerView.layoutManager = LinearLayoutManager(this)
        groupRecyclerView.hasFixedSize()

        groupArrayList = arrayListOf<GroupMeeting>()

        getAllMeetings()

        Handler().postDelayed({
            progressDialog.dismiss()
        },4000)

    }
    private fun getAllMeetings(){
        dbRef = FirebaseDatabase.getInstance().getReference("ZoomMeetings")
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching data.....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if (snapshot.exists()){
                    for(zoomSnapshot in snapshot.children){
                        val zoom = zoomSnapshot.getValue(GroupMeeting::class.java)
                        groupArrayList.add(zoom!!)
                    }
                    groupRecyclerView.adapter = GroupTherapyAdapter(groupArrayList,this@GroupTherapyActivity)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }
        })
    }

    override fun onItemClick(position: Int) {
        val zoomIntent = Intent(Intent.ACTION_VIEW,
        Uri.parse("https://us02web.zoom.us/meeting/83395241499?occurrence=1653498000000"))
        startActivity(zoomIntent)
    }
}