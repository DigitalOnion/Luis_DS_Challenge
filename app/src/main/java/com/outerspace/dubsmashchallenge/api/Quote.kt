package com.outerspace.dubsmashchallenge.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Quote {
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    @SerializedName("previous")
    @Expose
    var previous: Any? = null

    @SerializedName("next")
    @Expose
    var next: String? = null
}