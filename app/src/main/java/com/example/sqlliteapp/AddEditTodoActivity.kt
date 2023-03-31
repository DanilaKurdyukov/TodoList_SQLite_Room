package com.example.sqlliteapp

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import com.example.sqlliteapp.data.TodoDatabase
import com.example.sqlliteapp.models.Todo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddEditTodoActivity : AppCompatActivity() {

    private val todoDao by lazy { TodoDatabase.getDB(this@AddEditTodoActivity).todoDao() }

    private lateinit var txtTitle: TextInputEditText
    private lateinit var txtDescription: TextInputEditText
    private lateinit var btnSave: MaterialButton
    private lateinit var current: Todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_todo)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        txtTitle = findViewById(R.id.edit_text_todoTitle)
        txtDescription = findViewById(R.id.edit_text_todoDescription)
        btnSave = findViewById(R.id.button_save)

        if (intent.extras!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                current = intent.extras!!.getSerializable("Todo", Todo::class.java)!!
            } else {
                current = intent.extras?.getSerializable("Todo") as Todo
            }
            txtTitle.setText(current.title)
            txtDescription.setText(current.description)
        }

        btnSave.setOnClickListener(View.OnClickListener {
            if(!this@AddEditTodoActivity::current.isInitialized){
                insertData(txtTitle?.text.toString(), txtDescription?.text.toString())
            }
            else {
                updateData(txtTitle.text.toString(), txtDescription.text.toString())
            }
        })
    }

    private fun insertData(title: String, description: String){

        val todo = Todo(0, title, description)
        todoDao.addAll(todo)
        startActivity(Intent(this@AddEditTodoActivity,MainActivity::class.java))
   }

    private fun updateData(title: String, description: String){
        val todo = Todo(current.id,title,description)
        todoDao.updateTodo(todo)
        startActivity(Intent(this@AddEditTodoActivity,MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        //dbHelper.close()
    }
}