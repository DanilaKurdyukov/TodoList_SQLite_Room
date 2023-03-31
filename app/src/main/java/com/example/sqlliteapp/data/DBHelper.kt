package com.example.sqlliteapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context,AppData.DB_NAME,null, AppData.DB_VERSION,) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(AppData.CREATE_TABLE_TODO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(AppData.DELETE_TABLE_TODO)
        onCreate(db)
    }
}