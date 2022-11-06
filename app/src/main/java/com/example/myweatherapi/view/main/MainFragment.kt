package com.example.myweatherapi.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapi.R
import com.example.myweatherapi.databinding.FragmentMainBinding
import com.example.myweatherapi.viewmodel.AppState
import com.example.myweatherapi.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val observer = Observer<Any>{renderData(it as AppState)}
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()
    }


    private fun renderData(data:AppState){
        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.text = "ERROR !!!"

            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.text = "SUCCESS !!!"
            }
        }


    }
    companion object{
        @JvmStatic
        fun newInstance() = MainFragment()
    }

  }