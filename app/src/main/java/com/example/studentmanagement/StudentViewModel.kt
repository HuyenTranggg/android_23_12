package com.example.studentmanagement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = StudentDatabaseHelper(application)
    private val _students = MutableLiveData<MutableList<Student>>()
    val students: LiveData<MutableList<Student>> = _students

    init {
        loadStudents()
    }

    private fun loadStudents() {
        _students.value = dbHelper.getAllStudents()
    }

    fun addStudent(student: Student) {
        dbHelper.addStudent(student)
        loadStudents()
    }

    fun updateStudent(oldMssv: String, updatedStudent: Student) {
        dbHelper.updateStudent(oldMssv, updatedStudent)
        loadStudents()
    }

    fun deleteStudent(student: Student) {
        dbHelper.deleteStudent(student.mssv)
        loadStudents()
    }
}
