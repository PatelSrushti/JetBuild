package com.meditab.jetbuild

import java.util.concurrent.TimeUnit

object AppUtils {
    fun getTimeDiff(toMillis: Long): Int {
        return (TimeUnit.DAYS.convert(
            toMillis - System.currentTimeMillis(),
            TimeUnit.MILLISECONDS
        ).toInt()
                )
    }
}