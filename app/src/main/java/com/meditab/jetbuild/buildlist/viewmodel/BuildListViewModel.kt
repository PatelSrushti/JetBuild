package com.meditab.jetbuild.buildlist.viewmodel

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.buildlist.repository.BuildListLiveData
import com.meditab.jetbuild.firebase.AppDatabase
import com.meditab.jetbuild.firebase.toValues

class BuildListViewModel(appData: AppData) : ViewModel() {
    val buildListLiveData: LiveData<MutableList<BuildData?>>

    init {
        buildListLiveData = getAppList(appData)
    }

    private fun getAppList(appData: AppData): LiveData<MutableList<BuildData?>> {

        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
        val buildListLiveData =
            BuildListLiveData(mDatabase.child(AppDatabase.FirebaseConstant.BUILDS).child(appData.id))
        return Transformations.map(buildListLiveData, Deserializer())

    }

    inner class Deserializer : Function<DataSnapshot, MutableList<BuildData?>> {

        override fun apply(dataSnapshot: DataSnapshot): MutableList<BuildData?> {
            val genericTypeIndicator = object : GenericTypeIndicator<Map<String, BuildData>?>() {}
            val apps = dataSnapshot.getValue(genericTypeIndicator)
            val list = apps?.toList().toValues()
            list.sortByDescending { it.buildNo }
            return list.toMutableList()
        }

    }

}
