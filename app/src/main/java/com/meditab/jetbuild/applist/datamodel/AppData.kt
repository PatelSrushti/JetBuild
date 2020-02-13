package com.meditab.jetbuild.applist.datamodel

import com.meditab.jetbuild.buildlist.datamodel.BuildData

data class AppData(
    var id: String,
    var name: String,
    var description: String? = null,
    var appIcon: Int? = null,
    var builds: List<BuildData>? = null
)