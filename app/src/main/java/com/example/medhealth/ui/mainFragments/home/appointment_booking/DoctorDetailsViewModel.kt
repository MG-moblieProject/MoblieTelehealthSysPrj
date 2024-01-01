package com.example.medhealth.ui.mainFragments.home.appointment_booking

import android.app.Application
import com.example.medhealth.base.BaseViewModel
import com.example.medhealth.model.User

class DoctorDetailsViewModel(application: Application) : BaseViewModel(application) {

    private var doctor: User = User()

    fun initDoctor(doctorDetails: User) {
        doctor = doctorDetails
    }

    fun getDoctor(): User {
        return doctor
    }

}
