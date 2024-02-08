package com.example.conttrackerjc.domain

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.conttrackerjc.ConTrackerApp
import com.example.conttrackerjc.R
import com.example.conttrackerjc.data.ContainerAPIService
import com.example.conttrackerjc.data.ContainerDTO
import com.example.conttrackerjc.data.ContainersDatabase
import com.example.conttrackerjc.data.toPartialContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BackgroundCallWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {

    private val containerID = inputData.getString("container")
    override suspend fun doWork(): Result {
        if (containerID != null) {
            compareData(containerID)
        }
        Log.d("Custom Worker", "Success!")
        return Result.success()
    }
}

fun showNotification(containerID: String, containerStatus: String) {
    val notification = NotificationCompat.Builder(ConTrackerApp.getAppContext(), "test_chanel")
        .setSmallIcon(R.drawable.container_crane)
        .setContentTitle("Změna statusu u kontejneru")
        .setContentText("U kontejneru $containerID došlo ke změně statusu na: $containerStatus")
        .build()
    val notificationManager = ConTrackerApp.getAppContext()
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(1, notification)
}

suspend fun compareData(container: String) {
    //Calls cached data from local database
    val db = ContainersDatabase.getDaoInstance(ConTrackerApp.getAppContext())
    val wanted = db.getContainerById(container)


    val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create()
    ).baseUrl("https://coast.hhla.de/").build()
    val containerInterface = retrofit.create(ContainerAPIService::class.java)

    //Calls server for new data
    containerInterface.getContainerInfo(contId = container)
        .enqueue(object : Callback<ContainerDTO> {
            override fun onResponse(
                call: Call<ContainerDTO>, response: Response<ContainerDTO>
            ) {
                response.body()?.let {
                    val newData = it.toPartialContainer()
                    if (wanted != null) {
                        //Compares cached local data with data brought from server, if different, send notification
                        if (newData.status != wanted.status) {
                            showNotification(newData.containerId, newData.status!!)

                            CoroutineScope(context = Dispatchers.IO).launch {
                                db.updateContainer(newData)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ContainerDTO>, t: Throwable) {
                t.printStackTrace()
            }
        })
}