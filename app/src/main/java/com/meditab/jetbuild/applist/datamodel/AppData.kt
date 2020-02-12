package com.meditab.jetbuild.applist.datamodel

import com.meditab.jetbuild.appdetails.datamodel.BuildData

data class AppData(
    var id: String,
    var name: String,
    var description: String? = null,
    var appIcon: String? = null,
    var builds: List<BuildData>? = null
)