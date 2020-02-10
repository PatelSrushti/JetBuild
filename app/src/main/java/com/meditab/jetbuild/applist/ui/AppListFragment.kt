package com.meditab.jetbuild.applist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.viewmodel.AppListViewModel

class AppListFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
