package com.medialink.kotlincorofrommindork.data.model


import com.google.gson.annotations.SerializedName

class Users : ArrayList<Users.UserItem>(){
    data class UserItem(
        @SerializedName("id")
        val userId: Int?,
        @SerializedName("name")
        val userName: String?,
        @SerializedName("avatar")
        val image: String?,
        @SerializedName("email")
        val userEmail: String?
    )
}