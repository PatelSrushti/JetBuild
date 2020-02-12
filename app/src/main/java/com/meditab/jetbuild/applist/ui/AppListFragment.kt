package com.meditab.jetbuild.applist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.adapter.AppListAdapter
import com.meditab.jetbuild.applist.viewmodel.AppListViewModel
import kotlinx.android.synthetic.main.app_list_fragment.*

class AppListFragment : Fragment() {

    private lateinit var mContext: Context
    companion object {
        fun newInstance() = AppListFragment()
    }

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
//        btnGo.setOnClickListener {
//            findNavController().navigate(R.id.action_appListFragment_to_appDetailsFragment, null)
//        }

        val adapter = AppListAdapter()
        rvApps.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(AppListViewModel::class.java)

        viewModel.appListLiveData.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
