package com.shzlabs.mausam

import android.os.Bundle
import com.shzlabs.mausam.ui.base.BaseActivity
import com.shzlabs.mausam.ui.home.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavMgr().pushFragment(this, HomeFragment.newInstance())

        getDiComponent().inject(this)
    }

    override fun onBackPressed() {
        if(!NavMgr().pop(this)) {
            finish()
        }
    }
}
