package com.meditab.jetbuild.buildlist.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.databinding.BuildListItemBinding
import java.util.concurrent.TimeUnit

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

            binding.llBuild.setBackgroundColor(Color.parseColor(appData.primaryColor))
            binding.buildNo.text = buildData.buildNo.toString()
            binding.buildNotes.text = buildData.notes
            binding.versionNo.text = buildData.version
            val diff = TimeUnit.DAYS.convert(
                buildData.expiryDate - System.currentTimeMillis(),
                TimeUnit.MILLISECONDS
            )
            binding.expiryDate.text = "$diff Days"
            binding.environment.text = buildData.getEnvironmentValue()
            binding.btnOpen.setOnClickListener {
                buildListListener.onClick(buildData)
            }

            try {

                val packageInfo = context.packageManager?.getPackageInfo(appData.packageName, 0)
                val versionCode =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) packageInfo?.longVersionCode?.toInt() else packageInfo?.versionCode
                binding.btnOpen.text = if (versionCode == buildData.buildNo) "Open" else "Get"

            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

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