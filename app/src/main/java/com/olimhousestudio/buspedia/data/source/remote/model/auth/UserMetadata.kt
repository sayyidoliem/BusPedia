package com.olimhousestudio.buspedia.data.source.remote.model.auth


import com.google.gson.annotations.SerializedName

data class UserMetadata(
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean,
    @SerializedName("sub")
    val sub: String,
    @SerializedName("username")
    val username: String
)