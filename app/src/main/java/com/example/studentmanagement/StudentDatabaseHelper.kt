package com.example.studentmanagement

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "StudentManagement.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "students"
        private const val COLUMN_MSSV = "mssv"
        private const val COLUMN_HOTEN = "hoTen"
        private const val COLUMN_SDT = "sdt"
        private const val COLUMN_DIACHI = "diaChi"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_MSSV TEXT PRIMARY KEY," +
                "$COLUMN_HOTEN TEXT," +
                "$COLUMN_SDT TEXT," +
                "$COLUMN_DIACHI TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllStudents(): MutableList<Student> {
        val studentList = mutableListOf<Student>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val student = Student(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MSSV)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOTEN)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SDT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIACHI))
                )
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return studentList
    }

    fun addStudent(student: Student): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MSSV, student.mssv)
            put(COLUMN_HOTEN, student.hoTen)
            put(COLUMN_SDT, student.sdt)
            put(COLUMN_DIACHI, student.diaChi)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateStudent(oldMssv: String, student: Student): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MSSV, student.mssv)
            put(COLUMN_HOTEN, student.hoTen)
            put(COLUMN_SDT, student.sdt)
            put(COLUMN_DIACHI, student.diaChi)
        }
        return db.update(TABLE_NAME, values, "$COLUMN_MSSV = ?", arrayOf(oldMssv))
    }

    fun deleteStudent(mssv: String): Int {
        val db = writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_MSSV = ?", arrayOf(mssv))
    }
}
