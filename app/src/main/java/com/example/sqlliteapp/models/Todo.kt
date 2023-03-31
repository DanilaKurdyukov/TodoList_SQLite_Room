package com.example.sqlliteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")

class Todo(
    @PrimaryKey(true) @ColumnInfo("todo_id") val id: Int = 1,
    @ColumnInfo("todo_title") val title: String,
    @ColumnInfo("todo_description") val description: String) : java.io.Serializable {

}