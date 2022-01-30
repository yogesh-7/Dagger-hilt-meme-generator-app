package com.example.dagger_hiltdemo.api

import com.example.dagger_hiltdemo.data.model.ApiResponse
import javax.inject.Inject

class MemeServiceImplementation @Inject constructor(private val memeService: MemeService){

    suspend fun fetchMemes(): ApiResponse =memeService.fetchMemesData()

}