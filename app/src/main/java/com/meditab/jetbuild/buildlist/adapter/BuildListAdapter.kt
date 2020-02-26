package com.meditab.jetbuild.buildlist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.AppUtils
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.databinding.BuildListItemBinding

class BuildListAdapter(
    private val buildListListener: BuildListListener,
    private val appData: AppData
) :
    ListAdapter<BuildData, BuildListAdapter.BuildListViewHolder>(BuildListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildListViewHolder {
        return BuildListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BuildListViewHolder, position: Int) {
        holder.bind(buildListListener, getItem(position), appData)
    }

    class BuildListViewHolder(private val binding: BuildListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            buildListListener: BuildListListener,
            buildData: BuildData,
            appData: AppData
        ) {

            binding.llBuild.setBackgroundColor(Color.parseColor(appData.primaryColor))
            binding.buildNo.text = "#${buildData.buildNo}"
            binding.buildNotes.text = buildData.notes
            binding.versionNo.text = buildData.version
            val diff = AppUtils.getTimeDiff(buildData.expiryDate)
            binding.expiryDate.text = "$diff Days"
            binding.environment.text = buildData.getEnvironmentValue()
            binding.btnOpen.setOnClickListener {
                buildListListener.onClick(buildData)
            }
        }

        companion object {
            fun from(parent: ViewGroup): BuildListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BuildListItemBinding.inflate(layoutInflater, parent, false)
                return BuildListViewHolder(binding)
            }
        }

    }

}

class BuildListListener(val clickListener: (buildData: BuildData) -> Unit) {
    fun onClick(buildData: BuildData) = clickListener(buildData)
}

class BuildListDiffCallback : DiffUtil.ItemCallback<BuildData>() {

    override fun areItemsTheSame(oldItem: BuildData, newItem: BuildData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BuildData, newItem: BuildData): Boolean {
        return oldItem == newItem
    }

}