package com.medialink.kotlincorofrommindork.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.medialink.kotlincorofrommindork.data.repository.MainRepository
import com.medialink.kotlincorofrommindork.ui.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "error occured!"))
        }
    }

}