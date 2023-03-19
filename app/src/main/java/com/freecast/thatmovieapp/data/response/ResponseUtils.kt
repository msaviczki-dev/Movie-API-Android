package com.freecast.thatmovieapp.data.response

import retrofit2.HttpException
import retrofit2.Response

fun <D> Response<D>.data(): D = if (isSuccessful) {
    body()!!
} else {
    throw HttpException(this)
}