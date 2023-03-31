package com.example.sqlliteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqlliteapp.models.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun todoDao() : TodoDAO

    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? =  null

        fun getDB(context: Context): TodoDatabase{
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE = buildDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDB(context: Context): TodoDatabase {
            return Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todos_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}