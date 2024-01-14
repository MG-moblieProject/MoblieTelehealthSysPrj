package com.example.metelehealth.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.metelehealth.R
import com.example.metelehealth.model.DoctorsProfile

class DoctorsProfileAdapter(private val newList : ArrayList<DoctorsProfile>)  : RecyclerView.Adapter<DoctorsProfileAdapter.MyViewHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener : onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doctor_profile_layout,parent,false)

        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentDoc = newList[position]

        holder.docImage.setImageResource(currentDoc.imageProfile)
        holder.docName.text = currentDoc.doctorName
        holder.docQuote.text = currentDoc.quote
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    class MyViewHolder(itemView : View, listener : onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val docImage : ImageView = itemView.findViewById(R.id.iv_doctor_profile)
        val docName : TextView = itemView.findViewById(R.id.doctor_chat_userName)
        val docQuote : TextView = itemView.findViewById(R.id.tv_quote_chat_doctor)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }
}