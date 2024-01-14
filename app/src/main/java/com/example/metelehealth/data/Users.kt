package com.example.metelehealth.data

// This is the data class that collects userNames for prescription posting by the doctors
data class Users(
    val email: String?= null,
    val fullName : String?= null,
    val age: String?= null) {
}