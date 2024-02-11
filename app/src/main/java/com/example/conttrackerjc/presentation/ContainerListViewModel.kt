package com.example.conttrackerjc.presentation


import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.conttrackerjc.ConTrackerApp
import com.example.conttrackerjc.data.Container
import com.example.conttrackerjc.data.ContainerAPIService
import com.example.conttrackerjc.data.ContainerDTO
import com.example.conttrackerjc.data.ContainersDatabase
import com.example.conttrackerjc.data.PartialContainer
import com.example.conttrackerjc.data.toContainer
import com.example.conttrackerjc.data.toPartialContainer
import com.example.conttrackerjc.domain.BackgroundCallWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit

class ContainerListViewModel : ViewModel(

) {
    private var containerInterface: ContainerAPIService
    private val db = ContainersDatabase.getDaoInstance(ConTrackerApp.getAppContext())
    private val _stateFlow = MutableStateFlow(ContainerListState())

    val stateFlow: StateFlow<ContainerListState>
        get() = _stateFlow.asStateFlow()

    fun switchNotificationPermission(result: Boolean) {
        _stateFlow.update {
            it.copy(hasNotificationPermission = result)
        }
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl("https://coast.hhla.de/").build()
        containerInterface = retrofit.create(ContainerAPIService::class.java)
    }

    fun getContainers() {
        viewModelScope.launch {
            db.getContainers().collect { response ->
                _stateFlow.update {
                    it.copy(containerList = response)
                }
            }
        }
    }

    private val periodicWorker = PeriodicWorkRequestBuilder<BackgroundCallWorker>(
        repeatInterval = 15, repeatIntervalTimeUnit = TimeUnit.MINUTES
    )

    private val workManager = WorkManager.getInstance(ConTrackerApp.getAppContext())

    fun updateNotification(id: String, notify: Boolean, uuid: UUID) {
        if (notify) {
            val data = Data.Builder()
            data.putString("container", id)
            workManager.enqueue(periodicWorker.setId(uuid).setInputData(data.build()).build())
            //workManager.beginUniqueWork("Test", ExistingWorkPolicy.KEEP, notificationRequest)
            //.enqueue()
        } else {
            workManager.cancelWorkById(uuid)
            workManager.pruneWork()
            viewModelScope.launch(Dispatchers.IO) {
                db.updateUuid(id, UUID.randomUUID())
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            db.updateNotification(id, notify)
        }
    }

    fun insertContainer(container: Container) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertContainer(container)
        }
    }

    fun getContainerById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val wanted = db.getContainerById(id)
            _stateFlow.update {
                it.copy(wantedCont = wanted!!)
            }
        }
    }

    fun deleteContainer(container: Container) {
        workManager.cancelWorkById(container.uuid)
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteContainer(container)
        }
        switchDeleteDialog()
    }

    fun getContainer(container: String, note: String) {
        containerInterface.getContainerInfo(contId = container)
            .enqueue(object : Callback<ContainerDTO> {
                override fun onResponse(
                    call: Call<ContainerDTO>, response: Response<ContainerDTO>
                ) {
                    if (response.code() == 204) {
                        updateIdText("")
                        updateNoteText("")
                        switchDialog()
                        Toast.makeText(
                            ConTrackerApp.getAppContext(),
                            "Kontejner nenalezen",
                            Toast.LENGTH_SHORT
                        ).show()
                        return
                    }

                    response.body()?.let {
                        println(it)
                        val newContainer = it.toContainer()
                        newContainer.note = note
                        insertContainer(newContainer)
                    }
                    updateIdText("")
                    updateNoteText("")
                    switchDialog()
                    Toast.makeText(
                        ConTrackerApp.getAppContext(),
                        "Kontejner nalezen a uložen do databáze.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<ContainerDTO>, t: Throwable) {
                    updateIdText("")
                    updateNoteText("")
                    switchDialog()
                    Toast.makeText(
                        ConTrackerApp.getAppContext(),
                        "Nedostupná síť",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun updateContainer(container: PartialContainer) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateContainer(container)
        }
    }

    fun getAndUpdateContainer(id: String) {
        containerInterface.getContainerInfo(contId = id).enqueue(object : Callback<ContainerDTO> {
            override fun onResponse(
                call: Call<ContainerDTO>, response: Response<ContainerDTO>
            ) {
                response.body()?.let {
                    updateContainer(it.toPartialContainer())
                }
            }

            override fun onFailure(call: Call<ContainerDTO>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun switchDialog() {
        _stateFlow.update {
            it.copy(enterIDText = "", enterNoteText = "", showDialog = !it.showDialog)
        }
    }

    fun switchDeleteDialog(){
        _stateFlow.update {
            it.copy(showDeleteDialog = !it.showDeleteDialog)
        }
    }

    fun assignToDelete(container: Container){
        _stateFlow.update {
            it.copy(containerToBeDeleted = container)
        }
    }

    fun updateIdText(text: String) {
        _stateFlow.update {
            it.copy(enterIDText = text.uppercase())
        }
    }

    fun updateNoteText(text: String) {
        _stateFlow.update {
            it.copy(enterNoteText = text)
        }
    }
}