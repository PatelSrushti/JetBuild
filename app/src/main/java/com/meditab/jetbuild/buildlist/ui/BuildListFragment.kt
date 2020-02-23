package com.meditab.jetbuild.buildlist.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.adapter.BuildListAdapter
import com.meditab.jetbuild.buildlist.adapter.BuildListListener
import com.meditab.jetbuild.buildlist.utils.BuildListViewModelFactory
import com.meditab.jetbuild.buildlist.viewmodel.BuildListViewModel
import kotlinx.android.synthetic.main.build_list_fragment.*

class BuildListFragment : Fragment() {

    private lateinit var viewModel: BuildListViewModel
    private var buildListFragmentArgs: BuildListFragmentArgs? = null
    private lateinit var appData: AppData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.build_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildListFragmentArgs = arguments?.let { BuildListFragmentArgs.fromBundle(it) }
        appData = buildListFragmentArgs?.appData ?: AppData()

        appToolbar.title = appData.name

        val viewModelFactory = BuildListViewModelFactory(appData)
        viewModel = ViewModelProvider(this, viewModelFactory).get(BuildListViewModel::class.java)

        val adapter = BuildListAdapter(BuildListListener {

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(it.link)
                )
            )

        }, appData)
        rvBuilds.adapter = adapter

        viewModel.buildListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
