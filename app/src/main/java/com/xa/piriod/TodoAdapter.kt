package com.xa.piriod

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class TodoAdapter(private val todos: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return TodoViewHolder(view)
    }
    fun addTodo (todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun deleteTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggle (tv:TextView,isCheked:Boolean){
        if (isCheked){
            tv.paintFlags = tv.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tv.paintFlags = tv.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todos[position]
        holder.itemView.apply {
            title_.text = current.title
            checkBox.isChecked = current.isChecked
            toggle(title_,checkBox.isChecked)
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                toggle(title_,isChecked)
                current.isChecked = !current.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}