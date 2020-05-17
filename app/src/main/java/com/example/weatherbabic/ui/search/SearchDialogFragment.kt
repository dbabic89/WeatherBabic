package com.example.weatherbabic.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.weatherbabic.R
import com.example.weatherbabic.data.database.entities.Location
import com.example.weatherbabic.ui.weather.WeatherCallback
import com.example.weatherbabic.utils.extensions.setDivider
import kotlinx.android.synthetic.main.dialog_search.*

class SearchDialogFragment(
    private val callback: WeatherCallback,
    private val locations: List<Location>
) : DialogFragment() {

    private val locationListener = object : LocationListener {
        override fun onLocationSelected(location: String) {
            callback.fetchWeather(location)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_search, container, false)
    }

    override fun onStart() {
        super.onStart()
        setDialogFullscreenMode()
        setAdapter()
        setCloseButton()
        setEditText()
    }

    private fun setCloseButton() {
        buttonClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setDialogFullscreenMode() {
        dialog?.let {
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    private fun setEditText() {
        editTextSearch.requestFocus()
        editTextSearch.setOnKeyListener { _, _, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                callback.fetchWeather(editTextSearch.text.toString())
                dismiss()
                return@setOnKeyListener true
            }

            false
        }
    }

    private fun setAdapter() {
        val adapter = LocationsAdapter(locationListener)
        adapter.addItems(locations)
        recyclerViewLocations.adapter = adapter
        recyclerViewLocations.setDivider(R.drawable.divider_blue_day)
        recyclerViewLocations.setOnClickListener {
            dismiss()
        }
    }
}