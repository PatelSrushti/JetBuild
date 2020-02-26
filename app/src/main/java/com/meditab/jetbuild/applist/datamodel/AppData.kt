package com.meditab.jetbuild.applist.datamodel

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import com.meditab.jetbuild.buildlist.datamodel.BuildData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppData(
    var id: String = "",
    var name: String = "",
    var icon: String? = null,
    @set:PropertyName("primary_color")
    @get:PropertyName("primary_color")
    var primaryColor: String = "#000",
    @get:PropertyName("latest_build")
    @set:PropertyName("latest_build")
    var latestBuild: BuildData? = null,
    @get:PropertyName("package_name")
    @set:PropertyName("package_name")
    var packageName: String = ""
) : Parcelable