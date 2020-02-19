package com.meditab.jetbuild.applist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.applist.repository.AppListDataProvider

class AppListViewModel : ViewModel() {
    val appListLiveData: LiveData<List<AppData>>

    init {
        appListLiveData = getAppList()
    }

    private fun getAppList(): LiveData<List<AppData>> {
        return AppListDataProvider().getAppList()
    }

}
