package com.example.conttrackerjc.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContainerAPIService {
    @GET("api/container/auskunft/{contId}")
    fun getContainerInfo(
        @Path("contId") contId: String
    ): Call<ContainerDTO>
}
