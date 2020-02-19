package com.meditab.jetbuild.buildlist.datamodel

data class BuildData(
    var id: String?,
    var version: String,
    var notes: String,
    var link: String,
    var environment: String,
    var created_date: Long,
    var expiry_date: Long,
    var build_no: Long

)