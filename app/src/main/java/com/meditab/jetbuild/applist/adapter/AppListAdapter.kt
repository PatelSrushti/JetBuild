package com.meditab.jetbuild.applist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.databinding.AppListItemBinding

class AppListAdapter :
    ListAdapter<AppData, AppListAdapter.AppListVH>(diffCallback) {

    class AppListVH(binding: AppListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvAppName = binding.tvAppName
        val ivAppLogo = binding.ivAppLogo
        val tvDescription = binding.tvDescription

        fun bind(appData: AppData) {
            tvAppName.text = appData.name
        }

        companion object {
            fun from(parent: ViewGroup):
                    AppListVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AppListItemBinding.inflate(layoutInflater, parent, false)
                return AppListVH(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListVH {
        return AppListVH.from(parent)
    }


    override fun onBindViewHolder(holder: AppListVH, position: Int) {
//        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<AppData>() {

            override fun areItemsTheSame(oldItem: AppData, newItem: AppData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AppData, newItem: AppData): Boolean {
                return oldItem == newItem
            }

        }
    }
}