package com.example.notbored.ui.activities.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.MainActivity
import com.example.notbored.databinding.FragmentActivityBinding
import com.example.notbored.databinding.TemplateActivityItemBinding

class ActivitiesViewHolder (binding: TemplateActivityItemBinding) : RecyclerView.ViewHolder(binding.root){
    private val binding = TemplateActivityItemBinding.bind(binding.root)
    fun bind(activity : String) {
        binding.tvActivity.text = activity
    }
}
