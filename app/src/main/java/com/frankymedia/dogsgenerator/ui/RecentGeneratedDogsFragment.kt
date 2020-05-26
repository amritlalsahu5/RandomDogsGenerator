package com.frankymedia.dogsgenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.frankymedia.dogsgenerator.adapter.RecentDogsRecyclerViewAdapters
import com.frankymedia.dogsgenerator.databinding.FragmentRecentDogsBinding
import com.frankymedia.dogsgenerator.viewmodel.GenerateDogsPageViewModel

/*
    Display all the recently cached Dogs
 */
class RecentGeneratedDogsFragment :Fragment(){

    private val viewModel: GenerateDogsPageViewModel by lazy {
        ViewModelProviders.of(this).get(GenerateDogsPageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRecentDogsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        //initializing binding object viewmodel
        binding.viewModel = viewModel

        //setting up the recyclerview adapter
        val adapter =
            RecentDogsRecyclerViewAdapters()
        val categoryLinearLayoutManager = LinearLayoutManager(context)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerRecentDogs.layoutManager = categoryLinearLayoutManager

        binding.recyclerRecentDogs.adapter = adapter

        viewModel.dogs.observe(this, Observer {

            adapter.setDogsList(it)
        })

        binding.btnClearDogs.setOnClickListener{
            viewModel.clearRecentDogsCache()
        }
        return binding.root
    }

}