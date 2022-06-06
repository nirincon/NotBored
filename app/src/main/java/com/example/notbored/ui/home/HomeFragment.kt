package com.example.notbored.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.notbored.R
import com.example.notbored.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        with(binding){
            tvConditions.setOnClickListener { goToTerms() }
        }
    }


    private fun goToTerms() {
        findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
    }
}

