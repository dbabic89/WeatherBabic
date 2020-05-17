package com.example.weatherbabic.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.databinding.ItemLocationBinding

class LocationsViewHolder(
    private val binding: ItemLocationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(locationListener: LocationListener, location: Location) {
        val locationName = location.name
        binding.labelLocation.text = locationName
        binding.root.setOnClickListener {
            locationListener.onLocationSelected(locationName)
        }
    }
}