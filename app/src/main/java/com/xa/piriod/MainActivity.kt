package com.xa.piriod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        button_add.setOnClickListener {
            val todoTitle = editText.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editText.text.clear()
            }else{
                editText.error = "empty"
            }


        }
        delete.setOnClickListener {
            todoAdapter.deleteTodo()
        }

    }
}