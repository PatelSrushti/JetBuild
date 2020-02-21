package com.meditab.jetbuild.buildlist.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meditab.jetbuild.buildlist.viewmodel.BuildListViewModel

class BuildListViewModelFactory(private val appId: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildListViewModel::class.java)) {
            return BuildListViewModel(appId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}