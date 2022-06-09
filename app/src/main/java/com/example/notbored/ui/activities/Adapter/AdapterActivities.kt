package com.example.notbored.ui.activities.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.databinding.TemplateActivityItemBinding

class AdapterActivities(
    private val activitiesList: List<String>,
    var onActivityListener: OnActivityListener
) : RecyclerView.Adapter<ActivitiesViewHolder>() {

    interface OnActivityListener {
        fun onActivityClick(activity: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val itemBinding =
            TemplateActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ActivitiesViewHolder(itemBinding)

        itemBinding.activity.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            onActivityListener.onActivityClick(activitiesList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val activity = activitiesList[position]
        holder.bind(activity)
    }

    override fun getItemCount(): Int = activitiesList.size
}