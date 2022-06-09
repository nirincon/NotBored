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
import com.example.notbored.databinding.FragmentActivitiesListBinding
import com.example.notbored.domain.repository.ActivityRepositoryImpl
import com.example.notbored.domain.rest.RetrofitClient
import com.example.notbored.presentation.ActivitiesViewModel
import com.example.notbored.presentation.ActivitiesViewModelFactory
import com.example.notbored.utils.ActivitiesType
import com.example.notbored.ui.activities.Adapter.AdapterActivities

/**
 * Fragment that shows the list of activities and the toolbar with the random option.
 */
class ActivitiesListFragment : Fragment(R.layout.fragment_activities_list),
    AdapterActivities.OnActivityListener {
    private lateinit var binding: FragmentActivitiesListBinding
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
        binding = FragmentActivitiesListBinding.bind(view)
        setPreferences()
        setShuffle()
        setAdapter(view)
    }

/**
 * the AdapterActivities instance is created and assigned to the RecyclerView.
 * @param view
 */
    private fun setAdapter(view: View) {
        adapter = AdapterActivities(activityList, this@ActivitiesListFragment)
        binding.listActivitiesRV.layoutManager = LinearLayoutManager(view.context)
        binding.listActivitiesRV.adapter = adapter
    }

/**
 * A toolbar was created for two fragments, therefore, the random icon must be visible,
 * the return icon hidden and the activities title assigned, since it is the view that shows these.
 * Clicking on the random image button executes the startFragmentHintSreen()
 * method but does not pass a specific activity as a parameter.
 */
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

/**
 * Add the activity to the sharedPreference AND navigate to the FragmentHintSreen fragment.
 * @param activity
 */
    private fun startFragmentHintSreen(activity: String) {
        viewModel.addType(activity.lowercase())
        findNavController().navigate(R.id.action_activityFragment_to_fragmentHintSreen)
    }

/**
 * Runs when a recycler activity is clicked.
 * @param activity
 */
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