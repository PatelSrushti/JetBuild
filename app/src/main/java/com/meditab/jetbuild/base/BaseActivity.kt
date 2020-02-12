package com.meditab.jetbuild.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        activity = this
    }

    protected fun loadFragment(containerId: Int, fragment: Fragment, tag: String? = "") {
        supportFragmentManager
            .beginTransaction()
            .add(containerId, fragment, "fragment_name")
            .commit()
    }
}
