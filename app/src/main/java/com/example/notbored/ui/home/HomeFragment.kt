package com.example.notbored.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.notbored.R
import com.example.notbored.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        init()
    }

    private fun init(){
        with(binding) {
            tvConditions.setOnClickListener { goToTerms() }
            etParticipants.doAfterTextChanged {
                val text = etParticipants.text.toString()
                if (text != "0") {
                    btnStart.isEnabled = true
                } else {
                    btnStart.isEnabled = false
                    etParticipants.error = "The number has to be greater than 0."
                }
            }
            btnStart.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToActivityFragment(
                    etParticipants.text.toString()
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun goToTerms() {
        findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
    }
}

