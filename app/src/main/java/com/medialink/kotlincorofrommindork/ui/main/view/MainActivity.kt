package com.medialink.kotlincorofrommindork.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.medialink.kotlincorofrommindork.R
import com.medialink.kotlincorofrommindork.data.api.ApiHelper
import com.medialink.kotlincorofrommindork.data.api.RetrofitBuilder
import com.medialink.kotlincorofrommindork.data.model.Users
import com.medialink.kotlincorofrommindork.ui.base.ViewModelFactory
import com.medialink.kotlincorofrommindork.ui.main.adapter.MainAdapter
import com.medialink.kotlincorofrommindork.ui.main.viewmodel.MainViewModel
import com.medialink.kotlincorofrommindork.ui.utils.Status
import kotlinx.android.synthetic.main.activity_main.*


// sumber: https://blog.mindorks.com/using-retrofit-with-kotlin-coroutines-in-android
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            retrieveList(it)
                        }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: Users) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(Users())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }
}