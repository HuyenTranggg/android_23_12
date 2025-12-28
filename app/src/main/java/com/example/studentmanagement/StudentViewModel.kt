package com.example.studentmanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _students = MutableLiveData<MutableList<Student>>(mutableListOf())
    val students: LiveData<MutableList<Student>> = _students

    init {
        // Initialize with some dummy data if needed
        _students.value = mutableListOf(
            Student("20210001", "Nguyễn Văn A", "0123456789", "Hà Nội"),
            Student("20210002", "Trần Thị B", "0987654321", "TP. HCM")
        )
    }

    fun addStudent(student: Student) {
        val currentList = _students.value ?: mutableListOf()
        currentList.add(student)
        _students.value = currentList
    }

    fun updateStudent(oldMssv: String, updatedStudent: Student) {
        val currentList = _students.value ?: return
        val index = currentList.indexOfFirst { it.mssv == oldMssv }
        if (index != -1) {
            currentList[index] = updatedStudent
            _students.value = currentList
        }
    }

    fun deleteStudent(student: Student) {
        val currentList = _students.value ?: return
        currentList.remove(student)
        _students.value = currentList
    }
}
