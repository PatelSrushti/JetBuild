package com.meditab.jetbuild

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("build_environment")
fun TextView.setBuildEnvironment(environment: Int) {
    text = if (environment == 1) "LIVE" else "BETA"
}

@BindingAdapter("build_expiry_date")
fun TextView.setBuildExpiryDate(expiryDate: Long) {
    text = "${AppUtils.getTimeDiff(expiryDate)} Days"
}

@BindingAdapter("build_card_color")
fun LinearLayout.setBuildCardColor(primaryColor: String) {
    setBackgroundColor(Color.parseColor(primaryColor))
}