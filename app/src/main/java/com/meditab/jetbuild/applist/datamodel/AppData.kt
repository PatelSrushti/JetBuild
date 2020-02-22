package com.meditab.jetbuild.applist.datamodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppData(
    var id: String = "",
    var name: String = "",
    var icon: String? = null,
    @SerialName("primary_color")
    var primaryColor: String = "",
    @SerialName("build_version")
    var buildVersion: String = "",
    var link: String = ""
)