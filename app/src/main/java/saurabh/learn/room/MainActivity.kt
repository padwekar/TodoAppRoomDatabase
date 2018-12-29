package saurabh.learn.room

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import saurabh.learn.room.database.DatabaseHelper
import saurabh.learn.room.database.Task
import saurabh.learn.room.database.UserDao




@ContentView(R.layout.activity_main)
class MainActivity : BaseActivity() , TaskAdapter.onActionClickListener {

    lateinit var databaseOps : UserDao
    lateinit var taskAdapter : TaskAdapter

    override fun onCreate() {

        databaseOps = DatabaseHelper.database(this).userDao()

        taskAdapter = TaskAdapter(databaseOps.getAllTask()).apply {
            actionListener = this@MainActivity
        }

        with(recyclerView){
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = taskAdapter
        }

        doneImageView.setOnClickListener {
            onAddClick(editText.text.toString())
        }

    }

    override fun onDeleteClick(item: String,position: Int) {
        taskAdapter.removeItemAt(position)
        databaseOps.deleteTask(Task(item))
    }

    private fun onAddClick(item: String){
        taskAdapter.add(item)
        databaseOps.insert(task = Task(item))
    }

}
