package com.example.sqlliteapp

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import com.example.sqlliteapp.data.AppData
import com.example.sqlliteapp.data.DBHelper
import com.example.sqlliteapp.models.Todo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddEditTodoActivity : AppCompatActivity() {

    val dbHelper = DBHelper(this@AddEditTodoActivity)

    var txtTitle: TextInputEditText? = null
    var txtDescription: TextInputEditText? = null
    var btnSave: MaterialButton? = null

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

        var current = Todo(0, "", "")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            current = intent.extras?.getSerializable("Todo", Todo::class.java)!!
        } else {
            current = intent.extras?.getSerializable("Todo") as Todo
        }

        txtTitle?.setText(current.title)
        txtDescription?.setText(current.description)

        btnSave?.setOnClickListener(View.OnClickListener {
            if (current==null){
                insertData(txtTitle?.text.toString(), txtDescription?.text.toString())
            } else {
                updateData(current.id,txtTitle?.text.toString(), txtDescription?.text.toString())
            }
        })
    }

    private fun insertData(title: String, description: String){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
           put(AppData.TodoContract.Todo.COLUMN_NAME_TITlE,title)
           put(AppData.TodoContract.Todo.COLUMN_NAME_DESCRIPTION,description)
        }
        val newRowId = db.insert(AppData.TodoContract.Todo.TABLE_NAME,null,values)
        val errorCode : Long = -1
        if (newRowId != errorCode){
            Toast.makeText(this@AddEditTodoActivity,"Задача добавлена!",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@AddEditTodoActivity,MainActivity::class.java))
        }
        db.close()
   }

    private fun updateData(id: Int, title: String, description: String){
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(AppData.TodoContract.Todo.COLUMN_NAME_TITlE, title)
            put(AppData.TodoContract.Todo.COLUMN_NAME_DESCRIPTION, description)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        db.update(AppData.TodoContract.Todo.TABLE_NAME, values, selection, selectionArgs)
        startActivity(Intent(this@AddEditTodoActivity,MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }
}