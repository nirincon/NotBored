package com.example.notbored.ui.activities


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
    private val args by navArgs<ActivityFragmentArgs>()
    private lateinit var adapter: AdapterActivities
    private lateinit var participants: String
    private lateinit var type: String
    private var activityList = ActivitiesType.listActivities
    private val viewModel by viewModels<ActivitiesViewModel>{
        ActivitiesViewModelFactory(
            ActivityRepositoryImpl(
                ActivitiesDataSource(RetrofitClient.apiservice)
            )
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)
        participants = viewModel.getParticipants()
        type = viewModel.getType()

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


        adapter = AdapterActivities(activityList, this@ActivityFragment)
        binding.listActivitiesRV.layoutManager = LinearLayoutManager(view.context)
        binding.listActivitiesRV.adapter = adapter
    }

    private fun startFragmentHintSreen(activity: String) {
        val action = ActivityFragmentDirections.actionActivityFragmentToFragmentHintSreen(
            activity.lowercase(),
            args.participants,
            args.minprice,
            args.maxprice
        )
        findNavController().navigate(action)
    }

    override fun onActivityClick(activity: String) {
        startFragmentHintSreen(activity)
    }
}