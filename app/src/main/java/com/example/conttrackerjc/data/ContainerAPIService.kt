package com.example.conttrackerjc.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContainerAPIService {
    @GET("api/container/auskunft/{contId}") //Everything after base URL, contID will be parameter in call
    fun getContainerInfo(
        @Path("contId") contId: String
    ): Call<ContainerDTO> //Call is returning class called ContainerDTO
}
