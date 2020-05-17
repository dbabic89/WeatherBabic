package com.example.weatherbabic.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.databinding.ItemLocationBinding

class LocationsAdapter(
    private val locationListener: LocationListener
) : RecyclerView.Adapter<LocationsViewHolder>() {

    private var list = mutableListOf<Location>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding =
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LocationsViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val item = list[position]
        holder.bind(locationListener, item)
    }

    fun addItems(items: List<Location>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}