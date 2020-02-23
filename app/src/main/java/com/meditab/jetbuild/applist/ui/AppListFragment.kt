package com.meditab.jetbuild.applist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.adapter.AppListAdapter
import com.meditab.jetbuild.applist.viewmodel.AppListViewModel
import kotlinx.android.synthetic.main.app_list_fragment.*

class AppListFragment : Fragment() {

    private lateinit var mContext: Context

    private lateinit var viewModel: AppListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.app_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {

        val adapter = AppListAdapter(mContext,
            AppListAdapter.AppListClickListener { appData ->
                findNavController().navigate(AppListFragmentDirections.actionAppListFragmentToBuildListFragment(appData))
            })
        rvApps.adapter = adapter

        viewModel = ViewModelProvider(this).get(AppListViewModel::class.java)

        viewModel.appListLiveData.observe(viewLifecycleOwner, Observer { list ->
            progressBar.visibility = GONE
            rvApps.visibility = View.VISIBLE
            adapter.submitList(list)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
