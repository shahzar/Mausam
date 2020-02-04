package com.shzlabs.mausam.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shzlabs.mausam.di.ViewModelFactory
import com.shzlabs.mausam.ui.base.BaseFragment
import com.shzlabs.mausam.R
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private var weatherListAdapter = WeatherListAdapter()
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

        rootView.weather_list.layoutManager = LinearLayoutManager(context)
        rootView.weather_list.adapter = weatherListAdapter

        rootView.fetch_data_button.setOnClickListener {

            val cities = search_edit_text.text
                .split(',')
                .filter { it.isNotEmpty() }

            if (cities.size < 3) {
                showInfo(rootView, getString(R.string.warn_min_cities))
                return@setOnClickListener
            }

            if(cities.size > 7) {
                showInfo(rootView, getString(R.string.warn_max_cities))
                return@setOnClickListener
            }

            weatherListAdapter.clearItems()
            viewModel.getWeatherData(cities)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherListAdapter.addItem(it) })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                progress_bar.visibility = View.VISIBLE
                weather_list.visibility = View.GONE
                return@Observer
            }

            progress_bar.visibility = View.GONE
            weather_list.visibility = View.VISIBLE
        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

    }

    override fun onAttach(context: Context) {
        getDiComponent().inject(this)
        super.onAttach(context)
    }

}
