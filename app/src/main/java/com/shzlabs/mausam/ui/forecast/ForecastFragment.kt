package com.shzlabs.mausam.ui.forecast


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shzlabs.mausam.R
import com.shzlabs.mausam.di.ViewModelFactory
import com.shzlabs.mausam.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forecast.view.*
import javax.inject.Inject

class ForecastFragment : BaseFragment() {

    companion object {
        fun newInstance() = ForecastFragment()
    }

    @Inject
    lateinit var forecastListAdapter: ForecastListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var rootView: View

    private lateinit var viewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_forecast, container, false)

        initView()

        return rootView
    }

    fun initView() {
        rootView.forecast_list.layoutManager = LinearLayoutManager(context)
        rootView.forecast_list.adapter = forecastListAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)

        viewModel.forecastData.observe(viewLifecycleOwner, Observer { forecastListAdapter.setItems(it.list) })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

        viewModel.getForecastData()
    }

    override fun onAttach(context: Context?) {
        getDiComponent().inject(this)
        super.onAttach(context)
    }

}
