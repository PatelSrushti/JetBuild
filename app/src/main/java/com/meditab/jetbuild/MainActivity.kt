package com.meditab.jetbuild

import android.os.Bundle
import com.meditab.jetbuild.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        loadFragment(R.id.fl_container, AppListFragment.newInstance())

    }
}
