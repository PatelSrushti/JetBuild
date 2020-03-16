package com.meditab.jetbuild.buildlist.adapter

import android.content.Context
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
    private val context: Context,
    private val buildListListener: BuildListListener,
    private val appData: AppData
) :
    ListAdapter<BuildData, BuildListAdapter.BuildListViewHolder>(BuildListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildListViewHolder {
        return BuildListViewHolder.from(parent, context)
    }

    override fun onBindViewHolder(holder: BuildListViewHolder, position: Int) {
        holder.bind(buildListListener, getItem(position), appData)
    }

    class BuildListViewHolder(
        private val binding: BuildListItemBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            buildListListener: BuildListListener,
            buildData: BuildData,
            appData: AppData
        ) {

            binding.app = appData
            binding.build = buildData
            binding.btnOpen.setOnClickListener {
                buildListListener.onClick(buildData)
            }
            binding.btnOpen.text = AppUtils.getOpenBuildText(
                context,
                appData.packageName,
                buildData.buildNo
            )
        }

        companion object {
            fun from(parent: ViewGroup, context: Context): BuildListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BuildListItemBinding.inflate(layoutInflater, parent, false)
                return BuildListViewHolder(binding, context)
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