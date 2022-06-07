package com.example.notbored.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notbored.R
import com.example.notbored.data.request.ActivitiesDataSource
import com.example.notbored.databinding.FragmentHomeBinding
import com.example.notbored.domain.repository.ActivityRepositoryImpl
import com.example.notbored.domain.rest.RetrofitClient
import com.example.notbored.presentation.ActivitiesViewModel
import com.example.notbored.presentation.ActivitiesViewModelFactory
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<ActivitiesViewModel>{
        ActivitiesViewModelFactory(
            ActivityRepositoryImpl(
                ActivitiesDataSource(RetrofitClient.apiservice)
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        init()

        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_prefs),
            Context.MODE_PRIVATE
        )
        if (sharedPreferences != null) {
            viewModel.setSharedPreferences(sharedPreferences)
        }

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
                if (cbAceptar.isChecked){
                    viewModel.addParticipants(etParticipants.text.toString())
                    val action = HomeFragmentDirections.actionHomeFragmentToActivityFragment(
                        etParticipants.text.toString(),"",""
                    )
                    findNavController().navigate(action)
                }else
                Snackbar
                    .make(binding.root,getString(R.string.accept_terms_and_conditions), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun goToTerms() {
        findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
    }
}

