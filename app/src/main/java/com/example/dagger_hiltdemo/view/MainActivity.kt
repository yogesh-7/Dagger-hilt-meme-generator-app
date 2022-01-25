package com.example.dagger_hiltdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.dagger_hiltdemo.databinding.ActivityMainBinding
import com.example.dagger_hiltdemo.util.ApiState
import com.example.dagger_hiltdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainActivityMainBinding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityMainBinding.root)
        initAPI()
        setObserver()
        onClick()

    }

    private fun onClick(){
        mainActivityMainBinding.btnRefresh.setOnClickListener {
            setObserver()

        }
    }

    private fun initAPI() {
        mainViewModel.getMemes()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            mainViewModel.memeStateFlow.collect { it ->
                when (it) {
                    is ApiState.SuccessSate -> {
                        mainActivityMainBinding.tvTitle.text = it.apiResponse.title
                        Glide
                            .with(this@MainActivity).load(it.apiResponse.url)
                            .centerCrop()
                            .into(mainActivityMainBinding.ivMeme)

                        mainActivityMainBinding.progressBar.visibility = View.GONE


                    }
                    is ApiState.FailureState -> {
                        mainActivityMainBinding.progressBar.visibility = View.GONE
                        mainActivityMainBinding.ivMeme.visibility = View.GONE
                        mainActivityMainBinding.tvTitle.visibility = View.GONE
                        mainActivityMainBinding.btnRefresh.visibility = View.GONE
                        Log.e("failure", it.msg.toString())

                    }

                    is ApiState.Loading -> {
                        mainActivityMainBinding.progressBar.visibility = View.VISIBLE

                    }

                }
            }
        }
    }


}