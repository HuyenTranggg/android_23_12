package com.example.studentmanagement

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(var mssv: String, var hoTen: String, var sdt: String, var diaChi: String): Parcelable