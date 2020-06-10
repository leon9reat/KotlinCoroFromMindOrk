package com.medialink.kotlincorofrommindork.data.api

import com.medialink.kotlincorofrommindork.data.model.Users
import retrofit2.http.GET

interface ApiService {
    @GET("e7290bc1-1327-4598-a8a4-53ade404f1f9")
    suspend fun getUsers(): Users
}