package saurabh.learn.room

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(var tasks: MutableList<String> = mutableListOf()) : RecyclerView.Adapter<ViewHolder>() {

    interface onActionClickListener {
        fun onDeleteClick(item : String,position: Int)
    }

    var actionListener: onActionClickListener? = null

    override fun onCreateViewHolder(vg: ViewGroup, p1: Int) =
        ViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_task,vg,false))

    override fun getItemCount() = tasks.count()

    override fun onBindViewHolder(p0: ViewHolder, position: Int) = with(p0.itemView) { with(tasks[p0.adapterPosition]) {
            taskTextView.text = this

            deleteImageView.setOnClickListener { actionListener?.onDeleteClick(this,p0.adapterPosition) }
     }
    }

    fun add(item : String)  {
        tasks.add(item)
        notifyItemInserted(tasks.size -1)
    }

    fun removeItemAt(pos : Int){
        tasks.removeAt(pos)
        notifyItemRemoved(pos)
    }

}


class ViewHolder(view : View) : RecyclerView.ViewHolder(view)