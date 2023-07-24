package com.ncs.retrofitapp

import retrofit2.Response
import retrofit2.http.GET

interface TodoAPI {

    @GET("/todos")
    suspend fun getTODOS():Response<List<TODO>>
}