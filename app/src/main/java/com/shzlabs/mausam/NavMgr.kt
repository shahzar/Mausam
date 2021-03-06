package com.shzlabs.mausam

import com.shzlabs.mausam.ui.base.BaseActivity
import com.shzlabs.mausam.ui.base.BaseFragment

object NavMgr {

    fun pushInitFragment(baseActivity: BaseActivity, baseFragment: BaseFragment) {

        if (baseActivity.supportFragmentManager.backStackEntryCount != 0) {
            return
        }

        baseActivity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.content, baseFragment)
            .commit()
    }

    fun pushFragment(baseActivity: BaseActivity, baseFragment: BaseFragment) {
        baseActivity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.content, baseFragment)
            .commit()
    }


    fun pop(baseActivity: BaseActivity): Boolean {

        if (baseActivity.supportFragmentManager.backStackEntryCount <= 1) {
            return false
        }

        baseActivity
            .supportFragmentManager
            .popBackStack()

        return true
    }
}