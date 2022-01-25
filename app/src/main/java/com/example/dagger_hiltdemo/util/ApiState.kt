package com.example.dagger_hiltdemo.util

import com.example.dagger_hiltdemo.data.ApiResponse

sealed class ApiState {

    class SuccessSate(var apiResponse: ApiResponse) : ApiState()
    class FailureState(var msg: Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
