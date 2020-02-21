package com.meditab.jetbuild.applist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.firebase.AppDatabase

class AppListDataProvider {
    fun getAppList(): LiveData<List<AppData>> {
        return AppDatabase().getAppListData()
//        return getList()
    }

    private fun getList(): MutableLiveData<List<AppData>> {
        val list = ArrayList<AppData>()

        list.add(
            AppData(
                id = "1",
                name = "IMSGo",
                icon = "https://firebasestorage.googleapis.com/v0/b/jet-build.appspot.com/o/IMSGo.png?alt=media&token=56cfe45a-9e50-42b4-a9e3-c541a0c22d51"
            )
        )
        list.add(
            AppData(
                id = "2",
                name = "IMSPatientApp",
                icon = "https://firebasestorage.googleapis.com/v0/b/jet-build.appspot.com/o/IMS%20Patient%20App.png?alt=media&token=cc9c09ba-ed4d-4dd7-9789-28dc534740d2"
            )
        )
        list.add(
            AppData(
                id = "3",
                name = "IMSOnArrival",
                icon = "https://firebasestorage.googleapis.com/v0/b/jet-build.appspot.com/o/IMS%20OnArrival.png?alt=media&token=14988c7f-4c2f-4746-bb2e-4dd2493cc9b9"
            )
        )

        val appListLiveData = MutableLiveData<List<AppData>>()
        appListLiveData.value = list
        return appListLiveData

    }
}