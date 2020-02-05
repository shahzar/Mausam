package com.shzlabs.mausam.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shzlabs.mausam.R
import com.shzlabs.mausam.di.components.AppComponent

open class BaseFragment: Fragment() {

    fun getDiComponent() : AppComponent {
        return (activity as BaseActivity).getDiComponent()
    }

    fun showError(view: View, msg: String) {
        val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(resources.getColor(R.color.snack_bar_error))
        snackbar.show()
    }

    fun showInfo(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }


}