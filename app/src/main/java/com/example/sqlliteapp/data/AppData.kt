package com.example.sqlliteapp.data

import android.provider.BaseColumns

class AppData {

    companion object TodoContract {
        const val DB_NAME = "Todo.db"
        const val DB_VERSION = 1
        const val CREATE_TABLE_TODO = "CREATE TABLE ${Todo.TABLE_NAME} (" +
                                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                                        "${Todo.COLUMN_NAME_TITlE} TEXT," +
                                        "${Todo.COLUMN_NAME_DESCRIPTION} TEXT)"

        const val DELETE_TABLE_TODO = "DROP TABLE IF EXISTS ${Todo.TABLE_NAME}"

        object Todo : BaseColumns{
            const val TABLE_NAME = "todo"
            const val COLUMN_NAME_TITlE = "title"
            const val COLUMN_NAME_DESCRIPTION = "description"
        }
    }
}