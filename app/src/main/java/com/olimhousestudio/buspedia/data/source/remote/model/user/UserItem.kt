package com.olimhousestudio.buspedia.data.source.remote.model.user


import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_admin")
    val isAdmin: Boolean,
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_image")
    val userImage: Any?,
    @SerializedName("user_name")
    val userName: String
)