package com.freecast.thatmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("results") val result: List<VideoDataResponse>?
) {
    data class VideoDataResponse(
        @SerializedName("key") val videoKey: String?
    )
}