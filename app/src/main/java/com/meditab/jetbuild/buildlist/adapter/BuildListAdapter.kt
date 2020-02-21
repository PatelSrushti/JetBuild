package com.meditab.jetbuild.buildlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.databinding.BuildListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class BuildListAdapter(private val buildListListener: BuildListListener) :
    ListAdapter<BuildData, BuildListAdapter.BuildListViewHolder>(BuildListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildListViewHolder {
        return BuildListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BuildListViewHolder, position: Int) {
        holder.bind(buildListListener, getItem(position))
    }

    class BuildListViewHolder(private val binding: BuildListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(buildListListener: BuildListListener, buildData: BuildData) {

            val formatter = SimpleDateFormat("M/d/yy, h:MM a")
            val expiryDate = formatter.format(Date(buildData.expiry_date))

            binding.buildNo.text = buildData.build_no.toString()
            binding.buildNotes.text = buildData.notes
            binding.expiryDate.text = expiryDate
            binding.environment.text = if (buildData.environment == 0) "Beta" else "Live"
            binding.version.text = buildData.version
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