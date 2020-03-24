package com.outerspace.dubsmashchallenge.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
}