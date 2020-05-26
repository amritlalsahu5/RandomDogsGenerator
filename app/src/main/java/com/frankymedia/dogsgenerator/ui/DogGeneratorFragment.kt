package com.frankymedia.dogsgenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.frankymedia.dogsgenerator.R
import com.frankymedia.dogsgenerator.databinding.FragmentGenerateDogsBinding
import com.frankymedia.dogsgenerator.viewmodel.GenerateDogsPageViewModel
import kotlinx.android.synthetic.main.fragment_generate_dogs.*

/*
    Class to generate dogs received through server call and cached/display it
 */
class DogGeneratorFragment :Fragment() {

    private val viewModel: GenerateDogsPageViewModel by lazy {
        ViewModelProviders.of(this).get(GenerateDogsPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGenerateDogsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        //initializing binding object viewmodel
        binding.viewModel = this.viewModel



        viewModel.recentDog.observe(this, Observer {
           // Nothing to be udpated here as data binding is handling data update in xml
        })
        viewModel.networkError.observe(this, Observer {
            if(viewModel.networkError.value!!) {
                Toast.makeText(context,getString(R.string.msg_network_error),Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_generate_dogs.setOnClickListener{
            viewModel.fetchDogsApiService()
        }
    }





}