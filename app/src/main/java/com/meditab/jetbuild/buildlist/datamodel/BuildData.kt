package com.meditab.jetbuild.buildlist.datamodel

import android.os.Parcelable
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuildData(
    @get:PropertyName("app_id")
    @set:PropertyName("app_id")
    var appId: String = "",
    @get:PropertyName("build_no")
    @set:PropertyName("build_no")
    var buildNo: Int = 0,
    @get:PropertyName("created_date")
    @set:PropertyName("created_date")
    var createdDate: Long = 0,
    @get:PropertyName("expiry_date")
    @set:PropertyName("expiry_date")
    var expiryDate: Long = 0,
    var environment: Int = 0,
    var id: String = "",
    var link: String = "",
    var notes: String = "",
    var version: String = ""
) : Parcelable