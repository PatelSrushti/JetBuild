package com.meditab.jetbuild

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.util.concurrent.TimeUnit

object AppUtils {
    fun getTimeDiff(toMillis: Long): Int {
        return (TimeUnit.DAYS.convert(
            toMillis - System.currentTimeMillis(),
            TimeUnit.MILLISECONDS
        ).toInt()
                )
    }

    fun getOpenBuildText(context: Context, packageName: String, buildNo: Int): String {
        return if (getPackageVersionCode(context, packageName) == buildNo) {
            context.getString(R.string.txt_open)
        } else {
            context.getString(R.string.txt_get)
        }
    }

    fun getPackageVersionCode(context: Context, packageName: String): Int? {
        return try {
            val packageInfo = context.packageManager?.getPackageInfo(packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) packageInfo?.longVersionCode?.toInt() else packageInfo?.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            0
        }
    }

}