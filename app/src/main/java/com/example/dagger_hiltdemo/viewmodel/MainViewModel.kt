package com.example.dagger_hiltdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger_hiltdemo.repository.MemeMainRepository
import com.example.dagger_hiltdemo.util.ApiState
import com.example.dagger_hiltdemo.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val memeMainRepository: MemeMainRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {






    val memeStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    init {
        getMemes()
    }

    fun checkInternetConnectionWithMessage(): Boolean = networkHelper.isNetworkConnected()

    fun getMemes(): Job? {
        return if (checkInternetConnectionWithMessage()) {
            viewModelScope.launch {
                memeStateFlow.value = ApiState.Loading
                memeMainRepository.getMemes().catch { e ->
                    memeStateFlow.value = ApiState.FailureState(e)
                }.collect { data ->
                    memeStateFlow.value = ApiState.SuccessSate(data)
                }

            }
        } else {
            return null
        }


    }

}