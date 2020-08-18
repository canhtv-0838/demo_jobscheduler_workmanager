package com.example.demo_jobscheduler_workmanager.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.widget.Toast
import com.example.demo_jobscheduler_workmanager.*

class NotificationJobService : JobService() {

    /**
     * Call  by the system once it determines it is time to run the job
     *
     * @param jobParameters Contains the information about the job
     * @return Boolean indicating whether or not the job was offloaded to separate thread.
     * @return true if job is offloaded to separate thread.
     */
    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        jobParameters?.let {
            val repeat = it.extras.getInt(KEY_PERSISTABLE_REPEAT_TIME)
            val runnable = Runnable {
                for (i in 0..repeat) {
                    makeStatusNotification(
                        getString(R.string.job_service_notification_channel_id),
                        getString(R.string.job_service_notification_channel_name),
                        getString(R.string.job_service_notification_channel_description),
                        NOTIFICATION_ID,
                        getString(R.string.job_service_notification_title),
                        getString(R.string.job_service_notification_message),
                        this
                    )
                    Log.d(LOG, "RUNNING")
                    sleep()
                }
                jobFinished(jobParameters, true)
                Log.d(LOG, "jobFinished")

            }
            Thread(runnable).start()
        }
        return true
    }

    /**
     * Called by the system when the job is running but the conditions are no
     * longer met.
     * It is never called since the job is not offloaded to a different thread.
     *
     * @param jobParameters Contains the information about the job.
     * @return Boolean indicating whether the job needs rescheduling.
     * @return true, the job is rescheduled, otherwise, the job is dropped.
     */
    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        Log.d(LOG, "STOP")
        Toast.makeText(this, getString(R.string.job_stop), Toast.LENGTH_SHORT).show()
        return true
    }

    companion object {
        // Notification channel ID.
        private const val NOTIFICATION_ID = 0
    }
}