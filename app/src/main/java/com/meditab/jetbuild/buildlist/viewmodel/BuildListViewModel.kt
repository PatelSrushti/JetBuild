package com.meditab.jetbuild.buildlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.buildlist.repository.BuildListLiveData
import com.meditab.jetbuild.firebase.AppDatabase

class BuildListViewModel(appData: AppData) : ViewModel() {
    val buildListLiveData: LiveData<List<BuildData>>

    init {
        buildListLiveData = getAppList(appData)
    }

    private fun getAppList(appData: AppData): LiveData<List<BuildData>> {

        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
        val buildListLiveData =
            BuildListLiveData(mDatabase.child(AppDatabase.FirebaseConstant.BUILDS).child(appData.id))
        return buildListLiveData
    }

}
