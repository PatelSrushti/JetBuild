package com.meditab.jetbuild.applist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.meditab.jetbuild.applist.adapter.AppListAdapter
import com.meditab.jetbuild.applist.viewmodel.AppListViewModel
import com.meditab.jetbuild.databinding.AppListFragmentBinding

class AppListFragment : Fragment() {

    private lateinit var mContext: Context

    private lateinit var viewModel: AppListViewModel

    private lateinit var binding: AppListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AppListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
    }

    private fun initializeView() {

        val adapter = AppListAdapter(mContext,
            AppListAdapter.AppListClickListener { appData ->
                findNavController().navigate(
                    AppListFragmentDirections.actionAppListFragmentToBuildListFragment(
                        appData
                    )
                )
            })
        binding.rvApps.adapter = adapter

        viewModel = ViewModelProvider(this).get(AppListViewModel::class.java)

        viewModel.appListLiveData.observe(viewLifecycleOwner, Observer { list ->
            binding.progressBar.visibility = GONE
            binding.rvApps.visibility = VISIBLE
            adapter.submitList(list)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
