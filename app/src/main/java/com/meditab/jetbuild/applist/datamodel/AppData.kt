package com.meditab.jetbuild.applist.datamodel

import com.google.firebase.database.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class AppData(
    var id: String = "",
    var name: String = "",
    var icon: String? = null,
    @set:PropertyName("primary_color")
    @get:PropertyName("primary_color")
    var primaryColor: String = "",
    @get:PropertyName("build_version")
    @set:PropertyName("build_version")
    var buildVersion: String = "",
    var link: String = ""
)