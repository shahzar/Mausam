package com.shzlabs.mausam.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shzlabs.mausam.di.ViewModelFactory
import com.shzlabs.mausam.ui.base.BaseFragment
import com.shzlabs.mausam.R
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    lateinit var rootView: View

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.home_fragment, container, false)

        initView()

        return rootView
    }

    private fun initView() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.sampleData.observe(viewLifecycleOwner, Observer { welcome_text.text = it.weather[0]?.description })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getWeatherData()
    }

    override fun onAttach(context: Context) {
        getDiComponent().inject(this)
        super.onAttach(context)
    }

}
