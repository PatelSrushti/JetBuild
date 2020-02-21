package com.meditab.jetbuild.buildlist.datamodel

data class BuildData(
    var app_id: String = "",
    var build_no: Int = 0,
    var created_date: Long = 0,
    var environment: Int = 0,
    var expiry_date: Long = 0,
    var id: String = "",
    var link: String = "",
    var notes: String = "",
    var version: String = ""
)