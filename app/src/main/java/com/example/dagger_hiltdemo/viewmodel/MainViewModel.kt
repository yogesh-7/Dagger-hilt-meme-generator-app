package com.example.dagger_hiltdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger_hiltdemo.repository.MemeMainRepository
import com.example.dagger_hiltdemo.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val memeMainRepository: MemeMainRepository) :
    ViewModel() {

    val memeStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

//    val _memeSateFlow: StateFlow<ApiState> = memeStateFlow

    fun getMemes() = viewModelScope.launch {
        memeStateFlow.value = ApiState.Loading
        memeMainRepository.getMemes().catch { e ->
            memeStateFlow.value = ApiState.FailureState(e)
        }.collect { data ->
            memeStateFlow.value = ApiState.SuccessSate(data)
        }


    }

}