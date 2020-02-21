package com.meditab.jetbuild.applist.datamodel


data class AppData(
    var id: String = "",
    var name: String = "",
    var icon: String? = null,
    var primary_color: String = "",
    var build_version: String = "",
    var link: String = ""
)