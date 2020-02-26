package com.meditab.jetbuild.buildlist.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.meditab.jetbuild.AppUtils
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.firebase.Deserializer

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
            dataSnapshot.let { snapshot ->

                val list = Deserializer<BuildData>().apply(dataSnapshot)
//                val list = snapshot.deserialize<BuildData>()
                list.sortByDescending { buildData -> buildData.buildNo }

                list.forEach {

                    if (AppUtils.getTimeDiff(it.expiryDate) == 0) {
                        reference.child(it.id).removeValue()
                    }

                }
                value = list
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

}