package com.meditab.jetbuild.appdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.meditab.jetbuild.R
import com.meditab.jetbuild.appdetails.viewmodel.AppDetailsViewModel

class AppDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AppDetailsFragment()
    }

    private lateinit var viewModel: AppDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.app_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
