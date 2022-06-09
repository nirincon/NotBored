package com.example.notbored.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.notbored.R
import com.example.notbored.databinding.FragmentTermsBinding


class TermsFragment : Fragment(R.layout.fragment_terms) {

    private lateinit var binding: FragmentTermsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTermsBinding.bind(view)

        with(binding) {
            btnBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }
}