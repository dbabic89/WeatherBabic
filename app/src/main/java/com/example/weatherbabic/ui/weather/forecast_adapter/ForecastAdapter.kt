package com.example.weatherbabic.ui.weather.forecast_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.databinding.ItemForecastBinding

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    private var list = mutableListOf<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding =
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ForecastViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun addItems(items: List<Forecast>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}