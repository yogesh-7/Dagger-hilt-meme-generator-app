package com.example.dagger_hiltdemo.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.dagger_hiltdemo.R
import com.example.dagger_hiltdemo.databinding.ActivityMainBinding
import com.example.dagger_hiltdemo.util.ApiState
import com.example.dagger_hiltdemo.util.Toaster
import com.example.dagger_hiltdemo.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
        setObserver()


    }

    private fun onClick(){
        binding.btnRefresh.setOnClickListener {
           if (mainViewModel.checkInternetConnectionWithMessage()) {
               mainViewModel.getMemes()
           }else{
               Toaster.show(this,getString(R.string.network_connection_error))
           }

        }
    }


    private fun setObserver() {
        lifecycleScope.launch {
            mainViewModel.memeStateFlow.collect { it ->
                when (it) {
                    is ApiState.SuccessSate -> {
                        binding.tvTitle.text = it.apiResponse.title
                       Glide
                            .with(this@MainActivity).load(it.apiResponse.url)
                            .into(binding.ivMeme)

                        binding.progressBar.visibility = View.GONE


                    }
                    is ApiState.FailureState -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            ivMeme.visibility = View.GONE
                            tvTitle.visibility = View.GONE
                            btnRefresh.visibility = View.GONE
                        }
                        Log.e("failure", it.msg.toString())

                    }

                    is ApiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE

                    }

                    else -> {
                        if (!mainViewModel.checkInternetConnectionWithMessage()) {
                            Toaster.show(this@MainActivity,getString(R.string.network_connection_error))
                        }
                    }
                }
            }
        }
    }


}