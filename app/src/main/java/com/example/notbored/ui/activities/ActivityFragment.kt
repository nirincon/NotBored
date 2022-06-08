package com.example.notbored.ui.activities


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notbored.R
import com.example.notbored.data.request.ActivitiesDataSource
import com.example.notbored.databinding.FragmentActivityBinding
import com.example.notbored.domain.repository.ActivityRepositoryImpl
import com.example.notbored.domain.rest.RetrofitClient
import com.example.notbored.presentation.ActivitiesViewModel
import com.example.notbored.presentation.ActivitiesViewModelFactory
import com.example.notbored.utils.ActivitiesType
import com.example.notbored.ui.activities.Adapter.AdapterActivities


class ActivityFragment : Fragment(R.layout.fragment_activity),
    AdapterActivities.OnActivityListener {
    private lateinit var binding: FragmentActivityBinding
    private lateinit var adapter: AdapterActivities
    private var activityList = ActivitiesType.listActivities
    private val viewModel by viewModels<ActivitiesViewModel> {
        ActivitiesViewModelFactory(
            ActivityRepositoryImpl(
                ActivitiesDataSource(RetrofitClient.apiservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)
        setPreferences()
        setShuffle()
        setAdapter(view)
    }

    private fun setAdapter(view: View) {
        adapter = AdapterActivities(activityList, this@ActivityFragment)
        binding.listActivitiesRV.layoutManager = LinearLayoutManager(view.context)
        binding.listActivitiesRV.adapter = adapter
    }

    private fun setShuffle() {
        with(binding) {
            with(toolbar) {
                iconBack.visibility = View.GONE
                iconShuffle.visibility = View.VISIBLE
                tittleTolbar.text = "Activities"
                iconShuffle.setOnClickListener {
                    startFragmentHintSreen("")
                }
            }
        }
    }

    private fun startFragmentHintSreen(activity: String) {
        viewModel.addType(activity.lowercase())
        findNavController().navigate(R.id.action_activityFragment_to_fragmentHintSreen)
    }

    override fun onActivityClick(activity: String) {
        startFragmentHintSreen(activity)
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