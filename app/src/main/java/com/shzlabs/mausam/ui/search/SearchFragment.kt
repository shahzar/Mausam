package com.shzlabs.mausam.ui.search

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
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    companion object {

        fun newInstance() = SearchFragment()
    }

    lateinit var rootView: View

    @Inject
    lateinit var weatherListAdapter: WeatherListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (!::rootView.isInitialized) {
            rootView = inflater.inflate(R.layout.search_fragment, container, false)
        }

        setTitle(getString(R.string.page_title_search))

        initView()

        return rootView
    }

    private fun initView() {

        rootView.weather_list.layoutManager = LinearLayoutManager(context)
        rootView.weather_list.adapter = weatherListAdapter

        rootView.fetch_data_button.setOnClickListener {

            val cities = search_edit_text.text
                .trim()
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        viewModel.weatherData.observe(viewLifecycleOwner, Observer { weatherListAdapter.addItem(it) })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {

            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
            weather_list.visibility = if (it) View.GONE else View.VISIBLE

        })

        viewModel.onError.observe(viewLifecycleOwner, Observer { showError(rootView, it) })

    }

    override fun onAttach(context: Context) {
        getDiComponent().inject(this)
        super.onAttach(context)
    }

}
