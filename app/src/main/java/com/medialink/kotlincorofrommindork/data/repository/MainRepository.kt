package com.medialink.kotlincorofrommindork.data.repository

import com.medialink.kotlincorofrommindork.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}