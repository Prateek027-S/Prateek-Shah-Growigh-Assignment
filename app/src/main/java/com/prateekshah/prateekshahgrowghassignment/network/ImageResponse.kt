package com.prateekshah.prateekshahgrowghassignment.network

import com.squareup.moshi.Json

data class ImageResponse(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @Json (name = "download_url") val downloadUrl: String
)