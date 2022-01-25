package com.example.dagger_hiltdemo.api

import com.example.dagger_hiltdemo.data.ApiResponse
import retrofit2.http.GET

interface MemeService {

    @GET("gimme")
    suspend fun fetchMemesData(): ApiResponse
}