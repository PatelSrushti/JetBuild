package com.meditab.jetbuild.applist.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.databinding.AppListItemBinding

class AppListAdapter(
    val context: Context,
    private val appListClickListener: AppListClickListener? = null
) :
    ListAdapter<AppData, AppListAdapter.AppListVH>(diffCallback) {

    inner class AppListVH(private val binding: AppListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            appData: AppData,
            appListClickListener: AppListClickListener?
        ) {
            binding.app = appData
            Glide.with(context).load(appData.icon).into(binding.ivAppLogo)

            itemView.setOnClickListener {
                appListClickListener?.onClick(appData)
            }

            binding.btnAction.setOnClickListener {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(appData.latestBuild?.link)
                    )
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListVH {
        return from(parent)
    }

    private fun from(parent: ViewGroup):
            AppListVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AppListItemBinding.inflate(layoutInflater, parent, false)
        return AppListVH(binding)
    }

    override fun onBindViewHolder(holder: AppListVH, position: Int) {
        holder.bind(getItem(position), appListClickListener)
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

    class AppListClickListener(val clickListener: (appData: AppData) -> Unit) {
        fun onClick(appData: AppData) = clickListener(appData)
    }
}