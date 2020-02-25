package com.meditab.jetbuild.buildlist.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import com.meditab.jetbuild.firebase.toValues
import java.util.concurrent.TimeUnit

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

            dataSnapshot.let {
                val genericTypeIndicator =
                    object :
                        GenericTypeIndicator<Map<String, BuildData>?>() {}

                val apps = dataSnapshot.getValue(genericTypeIndicator)
                val list = apps?.toList().toValues()

                list.forEach {

                    val diff = TimeUnit.DAYS.convert(
                        it.expiryDate - System.currentTimeMillis(),
                        TimeUnit.MILLISECONDS
                    )

                    if (diff.toInt() == 0) {
                        reference.child(it.id).removeValue()
                    }

                }

            }

            value = dataSnapshot
        }

        override fun onCancelled(databaseError: DatabaseError) {

        }

    }

}