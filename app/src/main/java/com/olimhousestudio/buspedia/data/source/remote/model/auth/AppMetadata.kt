package com.olimhousestudio.buspedia.data.source.remote.model.auth


import com.google.gson.annotations.SerializedName

data class AppMetadata(
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providers")
    val providers: List<String>
)