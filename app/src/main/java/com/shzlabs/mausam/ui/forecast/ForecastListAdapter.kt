package com.shzlabs.mausam.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shzlabs.mausam.R
import com.shzlabs.mausam.data.model.ForecastItem
import com.shzlabs.mausam.helper.DataHelper
import com.shzlabs.mausam.helper.DateUtil
import javax.inject.Inject

class ForecastListAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_TYPE_CONTENT = 0
    private val ITEM_TYPE_LABEL = 1
    private var items: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ITEM_TYPE_CONTENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
            return CustomViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_label, parent, false)
        return LabelViewHolder(view)

    }

    fun clearItems() {
        items.clear()
    }

    fun addItem(item: ForecastItem) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun setItems(newItems: List<ForecastItem>) {
        items.clear()

        val modItems = DataHelper.divideListByDay(newItems)
        items.addAll(modItems)

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position] is String) {
            return ITEM_TYPE_LABEL
        }
        return ITEM_TYPE_CONTENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder.itemViewType == ITEM_TYPE_LABEL) {
            val labelViewHolder = holder as LabelViewHolder
            val label = items[position] as String

            labelViewHolder.dayLabel.text = label

            return
        }

        val customViewHolder = holder as CustomViewHolder
        val forecastItem = items[position] as ForecastItem

        val minTemp = "${forecastItem.main.tempMin.toInt()}°"
        val maxTemp = "${forecastItem.main.tempMax.toInt()}°"

        // todo add checks
        // todo Description UI
        customViewHolder.timeTv.text = DateUtil.getTimeFromEpoc(forecastItem.dt)
        customViewHolder.temperatureTv.text = "$minTemp/${maxTemp}"
        customViewHolder.descriptionTv.text = DataHelper.capitalizeText(forecastItem.weather[0].description)
        customViewHolder.windSpeedTv.text = forecastItem.wind.speed.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTv = itemView.findViewById<TextView>(R.id.time_tv)
        val temperatureTv = itemView.findViewById<TextView>(R.id.temperature_tv)
        val descriptionTv = itemView.findViewById<TextView>(R.id.description_tv)
        val windSpeedTv = itemView.findViewById<TextView>(R.id.wind_speed_tv)
    }

    class LabelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dayLabel = itemView.findViewById<TextView>(R.id.day_label)
    }
}