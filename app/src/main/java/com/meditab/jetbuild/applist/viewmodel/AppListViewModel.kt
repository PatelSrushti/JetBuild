package com.meditab.jetbuild.applist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meditab.jetbuild.R
import com.meditab.jetbuild.applist.datamodel.AppData

class AppListViewModel : ViewModel() {
    val appListLiveData: LiveData<List<AppData>>

    init {
        appListLiveData = getAppList()
    }

    private fun getAppList(): LiveData<List<AppData>> {
        val list = ArrayList<AppData>()
        list.add(AppData(id = "1", name = "IMSGo", appIcon = R.drawable.ic_imsgo))
        list.add(AppData(id = "2", name = "IMSPatientApp", appIcon = R.drawable.ic_patient_app))
        list.add(AppData(id = "3", name = "IMSOnArrival", appIcon = R.drawable.ic_onarrival))

        val appListLiveData = MutableLiveData<List<AppData>>()
        appListLiveData.value = list

        return appListLiveData
    }

}
