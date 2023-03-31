package com.example.sqlliteapp.data

import androidx.room.*
import com.example.sqlliteapp.models.Todo

@Dao
interface TodoDAO {

    @Insert
    fun addAll(vararg todos: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getTodos(): List<Todo>

}