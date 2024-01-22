package saurabh.learn.room

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

annotation class ContentView(val resource : Int)

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        javaClass.getAnnotation(ContentView::class.java)?.resource?.let {
            setContentView(it)
        }
        val d = 
        onCreate();
    }

    abstract fun onCreate()
}
