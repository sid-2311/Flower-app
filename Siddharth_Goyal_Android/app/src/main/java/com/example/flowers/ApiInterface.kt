package com.example.flowers

import Flower
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("v1/86dc0d4f-652d-4fc3-80d3-573d02697bb8")
    fun getFlowers(): Call<List<Flower>>
}