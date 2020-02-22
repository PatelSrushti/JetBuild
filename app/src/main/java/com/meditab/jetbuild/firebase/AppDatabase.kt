package com.meditab.jetbuild.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.meditab.jetbuild.applist.datamodel.AppData
import com.meditab.jetbuild.firebase.AppDatabase.FirebaseConstant.APPS


class AppDatabase {
    object FirebaseConstant {
        const val ROOT = ""
        const val APPS = "apps"
        const val BUILDS = "builds"

    }

    private val TAG = AppDatabase::class.java.canonicalName
    private var mDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getAppListData(): LiveData<List<AppData>> {
        val appListData = MutableLiveData<List<AppData>>()
        mDatabase.child(APPS).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, dataSnapshot.value?.toString() ?: "NO DATA")
                dataSnapshot.let {
                    val genericTypeIndicator =
                        object :
                            GenericTypeIndicator<Map<String, AppData>?>() {}

                    val apps = dataSnapshot.getValue(genericTypeIndicator)
                    val list = apps?.toList().toValues()
                    list.sortBy { it.id }
                    appListData.value = list

                }
            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        return appListData
    }
}


fun <E, V> List<Pair<E, V>>?.toValues(): ArrayList<V> {

    val values = ArrayList<V>()
    this?.forEach { pair ->
        values.add(pair.second)
    }

    return values
}
