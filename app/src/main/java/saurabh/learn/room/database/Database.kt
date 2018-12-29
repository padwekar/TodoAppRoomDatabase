package saurabh.learn.room.database
import android.arch.persistence.room.*
import android.content.Context

@Database(entities = [Task::class], version = 1,exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}

@Dao
interface UserDao {
    @Query("SELECT * FROM " + "TASKTABLE")
    fun getAllTask() : MutableList<String>

    @Delete
    fun deleteTask(task : Task)

    @Insert
    fun insert(task: Task)

}


@Entity(tableName = "TASKTABLE")
data class Task(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name : String
)

class DatabaseHelper private constructor(context: Context) {

    private var taskDb = Room.databaseBuilder(context,TaskDatabase::class.java,"TASKDATABSE").allowMainThreadQueries().build()

    companion object {
        private var INSTANCE : DatabaseHelper ?= null

        private fun getInstance(context: Context) : DatabaseHelper {
            if(INSTANCE == null)  { INSTANCE = DatabaseHelper(context)}
            return INSTANCE!!
        }

        fun database(context: Context) = getInstance(context).taskDb
    }

}

