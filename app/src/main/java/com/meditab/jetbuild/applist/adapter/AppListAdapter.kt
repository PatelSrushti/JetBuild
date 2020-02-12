package com.meditab.jetbuild.applist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.datamodel.AppData
import kotlinx.android.synthetic.main.app_list_item.view.*

class AppListAdapter(val context: Context, val mData: ArrayList<AppData>) :
    RecyclerView.Adapter<AppListAdapter.AppListVH>() {

    val layoutInflater = LayoutInflater.from(context)

    inner class AppListVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvAppName = view.tvAppName
        val ivAppLogo = view.ivAppLogo
        val tvDescription = view.tvDescription

        fun bind(appData: AppData) {
            tvAppName.text = appData.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListVH {
        return AppListVH(layoutInflater.inflate(R.layout.app_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AppListVH, position: Int) {
        holder.bind(mData[position])
    }
}