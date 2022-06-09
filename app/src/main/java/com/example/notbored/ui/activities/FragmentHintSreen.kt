package com.example.notbored.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.notbored.R
import com.example.notbored.data.request.ActivitiesDataSource
import com.example.notbored.databinding.FragmentHintSreenBinding
import com.example.notbored.domain.repository.ActivityRepositoryImpl
import com.example.notbored.domain.rest.RetrofitClient
import com.example.notbored.presentation.ActivitiesViewModel
import com.example.notbored.presentation.ActivitiesViewModelFactory
import com.example.notbored.utils.Resource
import com.google.android.material.snackbar.Snackbar

class FragmentHintSreen : Fragment(R.layout.fragment_hint_sreen) {
    lateinit var binding: FragmentHintSreenBinding
    private val viewModel by viewModels<ActivitiesViewModel> {
        ActivitiesViewModelFactory(
            ActivityRepositoryImpl(
                ActivitiesDataSource(RetrofitClient.apiservice)
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHintSreenBinding.bind(view)
        setPreferences()
        loadActivity()
        binding.buttonHintScreen.setOnClickListener { loadActivity() }
        binding.toolbar.iconBack.setOnClickListener { activity?.onBackPressed() }
    }

    /**
     * Consumption is made to the api and the data is loaded to the activity
     * Validation is implemented for loading the data in the two cases (Random, Activity Selection)*/

    private fun loadActivity() {
        viewModel.fetchActivities(
            viewModel.getParticipants(),
            viewModel.getType(),
            viewModel.getMin(),
            viewModel.getMax()
        ).observe(viewLifecycleOwner, Observer { activity ->
            when (activity) {
                is Resource.Loading -> {
                    binding.incluProgress.layoutProgress.visibility = View.VISIBLE
                    binding.RelativeHintScreen.visibility = View.GONE

                }
                is Resource.Success -> {
                    binding.incluProgress.layoutProgress.visibility = View.GONE
                    binding.toolbar.tittleTolbar.text = viewModel.getType()
                    binding.textNumParticipants.text = activity.data.participants
                    binding.textPriceCategory.text = getPrice(activity.data.price)
                    if (viewModel.getType().equals("")) {
                        binding.layoutActivityRandom.visibility = View.VISIBLE
                        binding.textActivityRandom.text = activity.data.type
                        binding.toolbar.tittleTolbar.text = resources.getString(R.string.random)
                    }
                    if (activity.data.error.equals(null)) {
                        binding.RelativeHintScreen.visibility = View.VISIBLE
                        binding.tittleHintScreen.text = activity.data.activity
                        binding.textNumParticipants.text = activity.data.participants
                    } else {
                        Snackbar
                            .make(
                                binding.root,
                                activity.data.error.toString(),
                                Snackbar.LENGTH_LONG
                            )
                            .show()
                    }

                }
                is Resource.Failure -> {
                    binding.incluProgress.layoutProgress.visibility = View.VISIBLE
                    binding.incluProgress.progressBar.visibility = View.GONE
                    binding.RelativeHintScreen.visibility = View.GONE
                    binding.incluProgress.textLoading.text = "Error ${activity.exception}"
                }
            }
        })
    }

/**
 * Returns the price range between free and high according to the returned value obtained in the service
 * @param price
 * @return
 */
    private fun getPrice(price: Float): String {
        return when (price) {
            0.0f -> "Free"
            in 0.0f..0.3f -> "Low"
            in 0.3f..0.6f -> "Medium"
            in 0.6f..1.0f -> "High"
            else -> ""
        }
    }

    private fun setPreferences() {
        val sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_prefs),
            Context.MODE_PRIVATE
        )
        if (sharedPreferences != null) {
            viewModel.setSharedPreferences(sharedPreferences)
        }
    }
}