package com.meditab.jetbuild.firebase

import android.util.Log
import com.google.firebase.database.*
import com.meditab.jetbuild.firebase.AppDatabase.FirebaseConstant.APPS


class AppDatabase {
    object FirebaseConstant {
        const val ROOT = ""
        const val APPS = "apps"
        const val BUILDS = "builds"

    }

    private val TAG = AppDatabase::class.java.canonicalName
    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getAppListData() {
        mDatabase.child(APPS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, dataSnapshot.value?.toString() ?: "NO DATA")
                dataSnapshot.let {
                    val genericTypeIndicator =
                        object :
                            GenericTypeIndicator<Map<String?, Object?>?>() {}

                    val apps = dataSnapshot.getValue(genericTypeIndicator)
                    val list = apps?.toList().toValues()
                    Log.d(TAG, "App name: ${list}")

                }
            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
}


fun <E, V> List<Pair<E, V>>?.toValues(): List<V> {

    val values = ArrayList<V>()
    this?.forEach { pair ->
        values.add(pair.second)
    }

    return values
}


//717
//
//        64 ~bugs
//        357 ~ 614