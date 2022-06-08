package com.example.notbored.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
    private val viewModel by viewModels<ActivitiesViewModel>{
        ActivitiesViewModelFactory(
            ActivityRepositoryImpl(
                ActivitiesDataSource(RetrofitClient.apiservice)
            )
        )

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHintSreenBinding.bind(view)
        loadActivity()
    }

    private fun loadActivity() {
        viewModel.fetchActivities(args.participants,args.type,args.minprice,args.maxprice).observe(viewLifecycleOwner, Observer { activity ->
            when(activity){
                is Resource.Loading ->{
                    binding.incluProgress.layoutProgress.visibility = View.VISIBLE
                    binding.RelativeHintScreen.visibility = View.GONE

                }
                is Resource.Success ->{
                    binding.incluProgress.layoutProgress.visibility = View.GONE
                    if (activity.data.error.equals(null)){
                        binding.RelativeHintScreen.visibility = View.VISIBLE
                        binding.tittleHintScreen.text = activity.data.activity
                        binding.textNumParticipants.text = activity.data.participants
                    }else {
                        Snackbar
                            .make(
                                binding.root,
                                activity.data.error.toString(),
                                Snackbar.LENGTH_LONG
                            )
                            .show()
                    }

                }
                is Resource.Failure ->{
                    binding.incluProgress.layoutProgress.visibility = View.VISIBLE
                    binding.incluProgress.progressBar.visibility = View.GONE
                    binding.RelativeHintScreen.visibility = View.GONE
                    binding.incluProgress.textLoading.text = "Error ${activity.exception}"
                }
            }
        })
    }


}