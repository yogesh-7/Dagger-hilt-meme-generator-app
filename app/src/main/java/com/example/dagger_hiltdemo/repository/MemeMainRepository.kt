package com.example.dagger_hiltdemo.repository

import com.example.dagger_hiltdemo.api.MemeServiceImplementation
import com.example.dagger_hiltdemo.data.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MemeMainRepository @Inject constructor(private val memeServiceImplementation: MemeServiceImplementation){

    fun getMemes(): Flow<ApiResponse> = flow{

        emit(memeServiceImplementation.fetchMemes())

    }.flowOn(Dispatchers.IO)

}