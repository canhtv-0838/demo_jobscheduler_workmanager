package com.example.demo_jobscheduler_workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demo_jobscheduler_workmanager.jobscheduler.JobSchedulerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startWithJobScheduler(view: View) {
        startActivity(Intent(this, JobSchedulerActivity::class.java))
    }

    fun startWithWorkManager(view: View) {
//        startActivity(Intent(this, BlurActivity::class.java))
    }
}