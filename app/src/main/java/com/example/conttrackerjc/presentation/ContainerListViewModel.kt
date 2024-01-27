package com.example.conttrackerjc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conttrackerjc.ConTrackerApp
import com.example.conttrackerjc.data.Container
import com.example.conttrackerjc.data.ContainerAPIService
import com.example.conttrackerjc.data.ContainerDTO
import com.example.conttrackerjc.data.ContainersDatabase
import com.example.conttrackerjc.data.toContainer
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

class ContainerListViewModel : ViewModel(

) {
    private var containerInterface: ContainerAPIService
    private val db = ContainersDatabase.getDaoInstance(ConTrackerApp.getAppContext())
    private val _stateFlow = MutableStateFlow(ContainerListState())
    val stateFlow: StateFlow<ContainerListState>
        get() = _stateFlow.asStateFlow()

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://coast.hhla.de/")
            .build()
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

    fun updateNotification(id: String, notify: Boolean){
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
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteContainer(container)
        }
    }

    fun getContainer(container: String) {
        containerInterface.getContainerInfo(contId = container).enqueue(
            object : Callback<ContainerDTO> {
                override fun onResponse(
                    call: Call<ContainerDTO>,
                    response: Response<ContainerDTO>
                ) {
                    response.body()?.let {
                        insertContainer(it.toContainer())
                    }
                    updateIdText("")
                    switchDialog()
                }

                override fun onFailure(call: Call<ContainerDTO>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )
    }

    fun switchDialog() {
        _stateFlow.update {
            it.copy(showDialog = !it.showDialog)
        }
    }

    fun updateIdText(text: String) {
        _stateFlow.update {
            it.copy(enterIDText = text.uppercase())
        }
    }
}