package com.shzlabs.mausam

import android.os.Bundle
import com.shzlabs.mausam.ui.base.BaseActivity
import com.shzlabs.mausam.ui.forecast.ForecastFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavMgr().pushFragment(this, ForecastFragment.newInstance())

        getDiComponent().inject(this)
    }

    override fun onBackPressed() {
        if(!NavMgr().pop(this)) {
            finish()
        }
    }
}
