package com.frankymedia.dogsgenerator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.frankymedia.dogsgenerator.R
import com.frankymedia.dogsgenerator.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

/*
    Home activity as a start of Navigation
 */
class HomeFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btn_generate_dogs.setOnClickListener{
            navController.navigate(R.id.action_homepage_to_generateDogsFragment)
        }
        btn_recent_dogs.setOnClickListener{
            navController.navigate(R.id.action_homepage_to_dogsListFragment)
        }


    }



}