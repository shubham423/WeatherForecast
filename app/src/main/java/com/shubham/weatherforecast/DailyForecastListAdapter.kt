package com.shubham.weatherforecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DailyForecastViewHolder(view: View,
                              private val tempDisplaySettingManager: TempDisplaySettingManager) : RecyclerView.ViewHolder(view) {

    private val tempText = view.findViewById<TextView>(R.id.tempText)
    private val descriptionText = view.findViewById<TextView>(R.id.descriptionText)

    fun bind(dailyForecast: DailyForecast) {
        tempText.text = formatTempForDisplay(dailyForecast.temp,tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = dailyForecast.description
    }
}

class DailyForecastListAdapter(
    private val clickHandler: (DailyForecast) -> Unit,
    private val tempDisplaySettingManager: TempDisplaySettingManager
) : androidx.recyclerview.widget.ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(itemView,tempDisplaySettingManager)
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
    }

    companion object {
        val DIFF_CONFIG = object: DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: DailyForecast,
                newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }


        }
    }
}