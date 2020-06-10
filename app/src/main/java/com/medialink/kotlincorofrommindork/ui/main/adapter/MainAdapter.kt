package com.medialink.kotlincorofrommindork.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medialink.kotlincorofrommindork.R
import com.medialink.kotlincorofrommindork.data.model.Users
import kotlinx.android.synthetic.main.row_item.view.*

class MainAdapter(private val users: Users): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(user: Users.UserItem) {
            itemView.apply {
                textViewUserName.text = user.userName
                textViewUserEmail.text = user.userEmail
                Glide.with(imageViewAvatar.context)
                    .load(user.image)
                    .into(imageViewAvatar)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: Users) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}