package com.outerspace.dubsmashchallenge.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("user_access")
    @Expose
    var userAccess: Boolean? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("tags")
    @Expose
    var tags: List<Any>? = null

    @SerializedName("waveform_raw_data")
    @Expose
    var waveformRawData: String? = null

    @SerializedName("creator")
    @Expose
    var creator: Creator? = null

    @SerializedName("cultural_selections")
    @Expose
    var culturalSelections: List<String>? = null

    @SerializedName("length")
    @Expose
    var length: Double? = null

    @SerializedName("quote_type")
    @Expose
    var quoteType: Any? = null

    @SerializedName("liked")
    @Expose
    var liked: Boolean? = null

    @SerializedName("language")
    @Expose
    var language: Any? = null

    @SerializedName("origin")
    @Expose
    var origin: Any? = null

    @SerializedName("num_likes")
    @Expose
    var numLikes: Int? = null

    @SerializedName("count_play")
    @Expose
    var countPlay: Int? = null

    @SerializedName("count_share")
    @Expose
    var countShare: Int? = null

    @SerializedName("num_shares")
    @Expose
    var numShares: Int? = null

    @SerializedName("num_plays")
    @Expose
    var numPlays: Int? = null

    @SerializedName("num_videos")
    @Expose
    var numVideos: Int? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("do_not_promote")
    @Expose
    var doNotPromote: Any? = null
}