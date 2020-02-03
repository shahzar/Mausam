package com.shzlabs.mausam.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shzlabs.mausam.di.components.AppComponent

open class BaseFragment: Fragment() {

    fun getDiComponent() : AppComponent {
        return (activity as BaseActivity).getDiComponent()
    }

    fun showError(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

}