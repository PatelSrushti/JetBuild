package com.meditab.jetbuild.applist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.databinding.AppListItemBinding

class AppListAdapter(val context: Context) :
    ListAdapter<AppData, AppListAdapter.AppListVH>(diffCallback) {

    inner class AppListVH(val binding: AppListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvAppName = binding.tvAppName
        val ivAppLogo = binding.ivAppLogo
        val tvDescription = binding.tvDescription

        fun bind(appData: AppData) {
            binding.app = appData
            Glide.with(context).load(appData.icon).into(binding.ivAppLogo)
        }

//        companion object {
//            fun from(parent: ViewGroup):
//                    AppListVH {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = AppListItemBinding.inflate(layoutInflater, parent, false)
//                return AppListVH(binding)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListVH {
        return from(parent)
    }

    fun from(parent: ViewGroup):
            AppListVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AppListItemBinding.inflate(layoutInflater, parent, false)
        return AppListVH(binding)
    }

    override fun onBindViewHolder(holder: AppListVH, position: Int) {
        holder.bind(getItem(position))
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