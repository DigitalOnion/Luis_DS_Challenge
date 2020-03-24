package com.outerspace.dubsmashchallenge.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Creator {
    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null

    @SerializedName("first_name")
    @Expose
    var firstName: Any? = null

    @SerializedName("last_name")
    @Expose
    var lastName: Any? = null

    @SerializedName("has_public_profile")
    @Expose
    var hasPublicProfile: Any? = null

    @SerializedName("date_joined")
    @Expose
    var dateJoined: String? = null
}