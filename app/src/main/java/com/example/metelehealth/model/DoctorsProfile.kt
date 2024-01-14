package com.example.metelehealth.model

import androidx.annotation.DrawableRes

data class DoctorsProfile(@DrawableRes val imageProfile : Int, val doctorName : String,
                            val quote : String) {
}