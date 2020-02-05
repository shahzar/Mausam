package com.shzlabs.mausam.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shzlabs.mausam.R
import com.shzlabs.mausam.data.model.WeatherModel
import javax.inject.Inject

class WeatherListAdapter @Inject constructor(): RecyclerView.Adapter<WeatherListAdapter.CustomViewHolder>() {

    private var items: MutableList<WeatherModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return CustomViewHolder(view)
    }

    fun clearItems() {
        items.clear()
    }

    fun addItem(item: WeatherModel) {
        items.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val minTemp = "${items[position].main.tempMin.toInt()}°"
        val maxTemp = "${items[position].main.tempMax.toInt()}°"

        // todo add checks
        // todo Description UI
        holder.temperatureTv.text = "$minTemp/${maxTemp}"
        holder.countryTv.text = items[position].name
        holder.descriptionTv.text = items[position].weather[0].description
        holder.windSpeedTv.text = items[position].wind.speed.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryTv = itemView.findViewById<TextView>(R.id.country_tv)
        val temperatureTv = itemView.findViewById<TextView>(R.id.temperature_tv)
        val descriptionTv = itemView.findViewById<TextView>(R.id.description_tv)
        val windSpeedTv = itemView.findViewById<TextView>(R.id.wind_speed_tv)
    }
}