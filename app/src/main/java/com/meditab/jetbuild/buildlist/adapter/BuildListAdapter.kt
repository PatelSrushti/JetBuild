package com.meditab.jetbuild.buildlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.databinding.BuildListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildListAdapter :
    ListAdapter<BuildData, BuildListAdapter.BuildListViewHolder>(BuildListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(buildDataList: List<BuildData>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(buildDataList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildListViewHolder {
        return BuildListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BuildListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BuildListViewHolder(private val binding: BuildListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(buildData: BuildData) {
            binding.buildNo.text = buildData.build_no.toString()
            binding.buildNotes.text = buildData.notes
            binding.createdDate.text = buildData.created_date.toString()
            binding.expiryDate.text = buildData.expiry_date.toString()
            binding.environment.text = buildData.environment.toString()
            binding.link.text = buildData.link
            binding.version.text = buildData.version
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

class BuildListDiffCallback : DiffUtil.ItemCallback<BuildData>() {

    override fun areItemsTheSame(oldItem: BuildData, newItem: BuildData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BuildData, newItem: BuildData): Boolean {
        return oldItem == newItem
    }

}