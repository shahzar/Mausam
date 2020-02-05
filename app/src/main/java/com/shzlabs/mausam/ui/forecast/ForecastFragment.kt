package com.shzlabs.mausam.ui.forecast


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shzlabs.mausam.R
import com.shzlabs.mausam.di.ViewModelFactory
import com.shzlabs.mausam.helper.PermissionUtil
import com.shzlabs.mausam.ui.base.BaseActivity
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

        setTitle(getString(R.string.page_title_forecast))

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

        //viewModel.getForecastData()
        getLocation()
    }

    override fun onAttach(context: Context?) {
        getDiComponent().inject(this)
        super.onAttach(context)
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        val locManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!PermissionUtil.hasPermission(activity as BaseActivity, Manifest.permission.ACCESS_FINE_LOCATION) &&
            !PermissionUtil.hasPermission(activity as BaseActivity, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            val perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

            if (PermissionUtil.useRuntimePermissions()) {
                requestPermissions(perms, 1)
            }
            return
        }



        val gpsLastKnown = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val networkLastKnown = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        var lat = 0
        var lon = 0

        if (gpsLastKnown != null) {
            lat = gpsLastKnown.latitude.toInt()
            lon = gpsLastKnown.longitude.toInt()
        } else if (networkLastKnown != null) {
            lat = networkLastKnown.latitude.toInt()
            lon = networkLastKnown.longitude.toInt()
        } else {
            showError(rootView, getString(R.string.warn_gps_off))
            lat = 0
            lon = 0
            return
        }

        viewModel.getForecastData(lat, lon)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 1) {
            grantResults.forEach {
                if (it != PackageManager.PERMISSION_GRANTED) {
                    showError(rootView, getString(R.string.err_perm_required))
                    return
                }
            }
            getLocation()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
