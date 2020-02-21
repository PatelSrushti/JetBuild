package com.meditab.jetbuild.buildlist.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class BuildListLiveData(private val reference: DatabaseReference) :
    MutableLiveData<DataSnapshot>() {

    private val listener = MyValueEventListener()

    override fun onActive() {
        reference.addValueEventListener(listener)
    }

    override fun onInactive() {
        reference.removeEventListener(listener)
    }

    inner class MyValueEventListener : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            value = dataSnapshot
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }

    }

}