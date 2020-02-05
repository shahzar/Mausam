package com.shzlabs.mausam.ui.landing


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shzlabs.mausam.NavMgr

import com.shzlabs.mausam.R
import com.shzlabs.mausam.ui.base.BaseActivity
import com.shzlabs.mausam.ui.base.BaseFragment
import com.shzlabs.mausam.ui.forecast.ForecastFragment
import com.shzlabs.mausam.ui.search.SearchFragment
import kotlinx.android.synthetic.main.fragment_landing.view.*

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : BaseFragment() {

    lateinit var rootView: View

    companion object {
        fun newInstance() = LandingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_landing, container, false)

        initView()

        return rootView
    }

    fun initView() {
        rootView.launch_forecast_button.setOnClickListener {
            NavMgr.pushFragment(activity as BaseActivity, ForecastFragment.newInstance())
        }

        rootView.launch_search_button.setOnClickListener {
            NavMgr.pushFragment(activity as BaseActivity, SearchFragment.newInstance())
        }

    }

}
