package com.example.weatherbabic.ui.weather.forecast_adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.databinding.ItemForecastBinding
import com.example.weatherbabic.utils.formatWindSpeed

class ForecastViewHolder(
    private val binding: ItemForecastBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast) {
        binding.labelDate.text = forecast.date
        binding.imageWeather.setImageResource(forecast.icon)
        binding.labelTemperature.text = forecast.temperature
        binding.labelWindDirection.text = forecast.windDirection
        binding.labelWindSpeed.text = formatWindSpeed(forecast.windSpeed)
    }
}