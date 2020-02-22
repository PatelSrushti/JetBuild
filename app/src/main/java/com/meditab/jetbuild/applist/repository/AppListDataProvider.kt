package com.meditab.jetbuild.applist.repository

import androidx.lifecycle.LiveData
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.firebase.AppDatabase

class AppListDataProvider {
    fun getAppList(): LiveData<List<AppData>> {
        return AppDatabase().getAppListData()
    }

}