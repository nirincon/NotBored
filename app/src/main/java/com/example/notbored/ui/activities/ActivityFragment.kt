package com.example.notbored.ui.activities


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import com.example.notbored.R

class ActivityFragment : Fragment(R.layout.fragment_activity) {
    private lateinit var binding: FragmentActivity
    private val args by navArgs<ActivityFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val participants = args.participants
        Log.d("PARTICIPANTES",participants)
    }

}