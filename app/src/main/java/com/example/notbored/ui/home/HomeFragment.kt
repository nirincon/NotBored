package com.example.notbored.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

/**
 * Initial fragment, allows you to enter the number of participants, select the price,
 * navigate to the terms and conditions snippet, and accept the terms and conditions
 * to advance to the next fragment.
 */
class HomeFragment : Fragment(R.layout.fragment_home), AdapterView.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<ActivitiesViewModel> {
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
        setPreferences()
    }

    /**
     * Initialize important functions on the UI.
     */
    private fun init() {
        binding.tvConditions.setOnClickListener { goToTerms() }
        textListener()
        startListener()
        setPriceOptions()
    }

    /**
     * set shared preferences.
     */
    private fun setPreferences() {
        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_prefs),
            Context.MODE_PRIVATE
        )
        if (sharedPreferences != null) {
            viewModel.setSharedPreferences(sharedPreferences)
        }
    }

/**
 * show pricing options
 */
    private fun setPriceOptions() {
        val priceOptions = resources.getStringArray(R.array.price_items)
        val adapter = ArrayAdapter(this.requireContext(), R.layout.item_list, priceOptions)
        binding.faqSelection.setAdapter(adapter)
        binding.faqSelection.onItemClickListener = this
    }

/**
 * By clicking the START button, verify that have accepted the terms and conditions
 * to proceed with the application.
 */
    private fun startListener() {
        binding.btnStart.setOnClickListener {
            if (binding.cbAceptar.isChecked) {
                viewModel.addParticipants(binding.etParticipants.text.toString())
                findNavController().navigate(R.id.action_homeFragment_to_activityFragment)
            } else
                Snackbar
                    .make(
                        binding.root,
                        getString(R.string.accept_terms_and_conditions),
                        Snackbar.LENGTH_SHORT
                    )
                    .show()
        }
    }

/**
 * Validate the selected price and save the maximum and minimum range to the sharedPreference.
 * @param string
 */
    private fun setValue(string: String) {
        return when (string) {
            in "Free" -> {
                viewModel.addMax("0")
                viewModel.addMin("0")
            }
            in "Low" -> {
                viewModel.addMax("0.3")
                viewModel.addMin("0")
            }
            in "Medium" -> {
                viewModel.addMax("0.6")
                viewModel.addMin("0.3")
            }
            in "High" -> {
                viewModel.addMax("1")
                viewModel.addMin("0.61")
            }
            else -> {
                viewModel.addMax("")
                viewModel.addMin("")
            }
        }
    }

/**
 * Validate that the number of participants entered is different from zero and enable the START button.
 */
    private fun textListener() {
        binding.etParticipants.doAfterTextChanged {
            val text = binding.etParticipants.text.toString()
            if (text != "0") {
                binding.btnStart.isEnabled = true
            } else {
                binding.btnStart.isEnabled = false
                binding.etParticipants.error = getString(R.string.error_greater_than_zero)
            }
        }
    }

/**
 * Navigate to the terms and conditions fragment.
 */
    private fun goToTerms() {
        findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        setValue(item)
    }
}