package com.meditab.jetbuild.buildlist.viewmodel

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.buildlist.repository.BuildListLiveData

class BuildListViewModel : ViewModel() {
    val buildListLiveData: LiveData<MutableList<BuildData?>>

    init {
        buildListLiveData = getAppList()
    }

    private fun getAppList(): LiveData<MutableList<BuildData?>> {

        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference
        val buildListLiveData = BuildListLiveData(mDatabase.child("builds"))
        return Transformations.map(buildListLiveData, Deserializer())

    }

    inner class Deserializer : Function<DataSnapshot, MutableList<BuildData?>> {

        override fun apply(dataSnapshot: DataSnapshot): MutableList<BuildData?> {
            val genericTypeIndicator = object : GenericTypeIndicator<Map<String?, BuildData?>?>() {}
            val apps = dataSnapshot.getValue(genericTypeIndicator)
            val list = apps?.toList().toValues()
            return list.toMutableList()
        }

    }

    private fun <E, V> List<Pair<E, V>>?.toValues(): List<V> {

        val values = ArrayList<V>()
        this?.forEach { pair ->
            values.add(pair.second)
        }

        return values
    }
}
