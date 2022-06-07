package com.example.notbored.ui.activities


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notbored.R
import com.example.notbored.databinding.FragmentActivityBinding
import com.example.notbored.model.Activities
import com.example.notbored.ui.activities.Adapter.AdapterActivities
import com.example.notbored.ui.activities.Adapter.OnActivityListener


class ActivityFragment : Fragment(R.layout.fragment_activity), OnActivityListener  {
    private lateinit var binding: FragmentActivityBinding
    private val args by navArgs<ActivityFragmentArgs>()
    private lateinit var adapter : AdapterActivities
    private var activityList = Activities.listActivities



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)
        val participants = args.participants
        Log.d("PARTICIPANTES",participants)

        with(binding){
            toolbar.iconBack.visibility = View.GONE
            toolbar.iconShuffle.visibility = View.VISIBLE
            toolbar.tittleTolbar.text = "Activities"
        }

        adapter = AdapterActivities(activityList,this@ActivityFragment)
        binding.listActivitiesRV.layoutManager= LinearLayoutManager(view.context)
        binding.listActivitiesRV.adapter = adapter
    }

    override fun onActivityListener(activity: String) {

    }

}