package com.example.dagger_hiltdemo.data.source

import com.example.dagger_hiltdemo.data.model.ApiResponse
import com.example.dagger_hiltdemo.data.source.MemeService
import javax.inject.Inject

class MemeServiceImplementation @Inject constructor(private val memeService: MemeService){

    suspend fun fetchMemes(): ApiResponse =memeService.fetchMemesData()

}