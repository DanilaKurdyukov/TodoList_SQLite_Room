package com.example.sqlliteapp

import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlliteapp.adapters.TodoAdapter
import com.example.sqlliteapp.data.ItemTouchHelperCallback
import com.example.sqlliteapp.data.TodoDatabase
import com.example.sqlliteapp.models.Todo
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val todoDao by lazy { TodoDatabase.getDB(this@MainActivity).todoDao() }
    private var todoRecycler : RecyclerView? = null
    private var todoAdapter : TodoAdapter? = null
    private var todoes = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        init()
        readData()
        enableSwipe()
    }

    override fun onPause() {
        super.onPause()
        todoes = arrayListOf()
        todoAdapter?.notifyDataSetChanged()
    }

    private fun readData() {
        todoes = todoDao.getTodos() as ArrayList<Todo>
        todoAdapter = TodoAdapter(this@MainActivity, todoes)
        todoRecycler?.adapter = todoAdapter
    }

    private fun init(){
        todoRecycler = findViewById(R.id.recycler_view_todoList)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.mainAppBar)
        setSupportActionBar(toolbar)

        findViewById<FloatingActionButton>(R.id.fab_addTodo).setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity,AddEditTodoActivity::class.java))
        })
    }

    private fun enableSwipe(){
        val swipeCallback = object : ItemTouchHelperCallback(this@MainActivity){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){
                    ItemTouchHelper.LEFT -> {
                        val current = todoes[viewHolder.position]
                        deleteTodo(current)
                        todoes.removeAt(viewHolder.position)
                        todoAdapter?.notifyItemRemoved(viewHolder.position)
                    }
                    ItemTouchHelper.RIGHT -> {
                        val current = todoes[viewHolder.position]
                        val editIntent = Intent(this@MainActivity,AddEditTodoActivity::class.java)
                        editIntent.putExtra("Todo",current)
                        startActivity(editIntent)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(todoRecycler)
    }

    private fun deleteTodo(current: Todo){
        todoDao.deleteTodo(current)
    }

    override fun onDestroy() {
        super.onDestroy()
        //dbHelper.close()
    }

}