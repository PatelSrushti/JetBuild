package com.meditab.jetbuild.applist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.adapter.AppListAdapter
import com.meditab.jetbuild.applist.datamodel.AppData
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
        btnGo.setOnClickListener {
            findNavController().navigate(R.id.action_appListFragment_to_appDetailsFragment, null)
        }

//        rvApps.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val list = ArrayList<AppData>()
        list.add(AppData("1", "IMSGo"))
        list.add(AppData("2", "IMSPatientApp"))
        list.add(AppData("3", "IMSOnArrival"))

        val adapter = AppListAdapter()

        rvApps.adapter = adapter
        adapter.submitList(list)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}
