package com.medialink.kotlincorofrommindork.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.medialink.kotlincorofrommindork.data.api.ApiHelper
import com.medialink.kotlincorofrommindork.data.repository.MainRepository
import com.medialink.kotlincorofrommindork.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}