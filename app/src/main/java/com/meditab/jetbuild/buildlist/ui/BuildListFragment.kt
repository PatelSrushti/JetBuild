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
import com.meditab.jetbuild.buildlist.adapter.BuildListAdapter
import com.meditab.jetbuild.buildlist.adapter.BuildListListener
import com.meditab.jetbuild.buildlist.viewmodel.BuildListViewModel
import kotlinx.android.synthetic.main.app_details_fragment.*

class BuildListFragment : Fragment() {

    companion object {
        fun newInstance() = BuildListFragment()
    }

    private lateinit var viewModel: BuildListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.app_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuildListViewModel::class.java)

        val adapter = BuildListAdapter(BuildListListener {

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(it.link)
                )
            )

        })
        rvBuilds.adapter = adapter

        viewModel.buildListLiveData.observe(viewLifecycleOwner, Observer {
            //            adapter.addHeaderAndSubmitList(it)
            adapter.submitList(it)
        })

    }

}
