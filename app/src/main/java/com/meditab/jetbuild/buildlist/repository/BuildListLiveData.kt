package com.meditab.jetbuild.buildlist.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.meditab.jetbuild.AppUtils
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.firebase.toValues

class BuildListLiveData(private val reference: DatabaseReference) :
    MutableLiveData<List<BuildData>>() {

    private val listener = MyValueEventListener()

    override fun onActive() {
        reference.addValueEventListener(listener)
    }

    override fun onInactive() {
        reference.removeEventListener(listener)
    }

    inner class MyValueEventListener : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {

//                val list = Deserializer<BuildData>().apply(dataSnapshot)
            val list: ArrayList<BuildData> = dataSnapshot.deserialize()
            list.sortByDescending { buildData -> buildData.buildNo }

            list.forEach {

                if (AppUtils.getTimeDiff(it.expiryDate) == 0) {
                    reference.child(it.id).removeValue()
                }

            }
            value = list
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

    fun DataSnapshot.deserialize(): ArrayList<BuildData> {
        val genericTypeIndicator = object : GenericTypeIndicator<Map<String, BuildData>?>() {}
        val apps = this.getValue(genericTypeIndicator)
        return apps?.toList().toValues()
    }

}