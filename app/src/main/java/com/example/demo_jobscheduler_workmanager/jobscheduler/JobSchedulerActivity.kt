
package com.example.demo_jobscheduler_workmanager.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.example.demo_jobscheduler_workmanager.KEY_PERSISTABLE_REPEAT_TIME
import com.example.demo_jobscheduler_workmanager.R

class JobSchedulerActivity : AppCompatActivity() {
    private var jobSchedule: JobScheduler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_scheduler)
    }

    fun scheduleJob(view: View) {
        jobSchedule = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val serviceName = ComponentName(packageName, NotificationJobService::class.java.name)

        val persistableBundle = PersistableBundle().apply {
            putInt(KEY_PERSISTABLE_REPEAT_TIME, 3)
        }
        val jobInfo = JobInfo.Builder(JOB_ID, serviceName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setExtras(persistableBundle)
            .build()

        jobSchedule?.schedule(jobInfo)
        Toast.makeText(this@JobSchedulerActivity, R.string.job_scheduled, Toast.LENGTH_SHORT)
            .show()
    }

    fun cancelJobScheduler(view : View) {
        jobSchedule?.cancelAll()
        jobSchedule = null
        Toast.makeText(this@JobSchedulerActivity, R.string.jobs_canceled, Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        private const val JOB_ID = 0
    }
}