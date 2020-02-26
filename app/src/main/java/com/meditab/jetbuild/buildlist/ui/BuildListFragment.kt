package com.meditab.jetbuild.buildlist.ui

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.adapter.BuildListAdapter
import com.meditab.jetbuild.buildlist.adapter.BuildListListener
import com.meditab.jetbuild.buildlist.utils.BuildListViewModelFactory
import com.meditab.jetbuild.buildlist.viewmodel.BuildListViewModel
import com.meditab.jetbuild.databinding.BuildListFragmentBinding

class BuildListFragment : Fragment() {

    private lateinit var viewModel: BuildListViewModel
    private var buildListFragmentArgs: BuildListFragmentArgs? = null
    private lateinit var appData: AppData
    private lateinit var binding: BuildListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BuildListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildListFragmentArgs = arguments?.let { BuildListFragmentArgs.fromBundle(it) }
        appData = buildListFragmentArgs?.appData ?: AppData()

        binding.appToolbar.title = appData.name

        val viewModelFactory = BuildListViewModelFactory(appData)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BuildListViewModel::class.java)

        val adapter = context?.let {
            BuildListAdapter(it, BuildListListener { buildData ->

                var packageInfo: PackageInfo? = null

                try {
                    packageInfo = it.packageManager.getPackageInfo(appData.packageName, 0)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }

                if (packageInfo != null) {
                    val launchIntent =
                        it.packageManager.getLaunchIntentForPackage(packageInfo.packageName)

                    val versionCode =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) packageInfo.longVersionCode.toInt() else packageInfo.versionCode

                    if (versionCode == buildData.buildNo) {
                        startActivity(launchIntent)
                    } else {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(buildData.link)))
                    }
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(buildData.link)))
                }

            }, appData)
        }

        binding.rvBuilds.adapter = adapter

        binding.filterFAB.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(appData.primaryColor))

        binding.filterFAB.setOnClickListener {

        }

        viewModel.buildListLiveData.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it)
        })
    }

}
