package com.meditab.jetbuild.applist.model

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.firebase.deserialize

class AppListLiveData(private val reference: DatabaseReference) :
    MutableLiveData<List<AppData>>() {

    private val listener = MyValueEventListener()

    override fun onActive() {
        reference.addValueEventListener(listener)
    }

    override fun onInactive() {
        reference.removeEventListener(listener)
    }

    inner class MyValueEventListener : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            dataSnapshot.let { dataSnapshot ->
                //                val list = Deserializer<AppData>().apply(dataSnapshot)
                val list = dataSnapshot.deserialize<AppData>()
                list.sortBy { it.id }

                value = list
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }
    }

}