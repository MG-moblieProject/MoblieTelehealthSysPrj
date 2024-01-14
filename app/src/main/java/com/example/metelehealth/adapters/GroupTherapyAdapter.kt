package com.example.metelehealth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.metelehealth.R
import com.example.metelehealth.data.GroupMeeting

class GroupTherapyAdapter(private val groupTherapyList: ArrayList<GroupMeeting>,private val listener : OnItemClickListener
                ) : RecyclerView.Adapter<GroupTherapyAdapter.MyViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout of
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.grouptherapy_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //get the current item in the recyclerView
        val currentZoomLink = groupTherapyList[position]

        holder.zoomTopic.text = currentZoomLink.zoomTopic
        holder.zoomDoctor.text = currentZoomLink.zoomDoctor
        holder.zoomDate.text = currentZoomLink.zoomDate

    }

    override fun getItemCount(): Int {
        return groupTherapyList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val zoomTopic: TextView = itemView.findViewById(R.id.tv_topic)
        val zoomDoctor: TextView = itemView.findViewById(R.id.tv_lead_doctor)
        val zoomDate: TextView = itemView.findViewById(R.id.date_zoom_meeting)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}

