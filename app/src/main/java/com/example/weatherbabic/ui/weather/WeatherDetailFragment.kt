package com.example.weatherbabic.ui.weather

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherbabic.ui.activities.MainActivity
import com.example.weatherbabic.R
import com.example.weatherbabic.ui.activities.VideoPlayerActivity
import com.example.weatherbabic.data.database.entities.Forecast
import com.example.weatherbabic.data.database.entities.Weather
import com.example.weatherbabic.data.database.entities.YoutubeVideo
import com.example.weatherbabic.databinding.FragmentWeatherDetailBinding
import com.example.weatherbabic.di.Injectable
import com.example.weatherbabic.ui.search.SearchDialogFragment
import com.example.weatherbabic.ui.weather.forecast_adapter.ForecastAdapter
import com.example.weatherbabic.utils.*
import com.example.weatherbabic.utils.extensions.capitalizeWords
import com.example.weatherbabic.utils.extensions.setDivider
import com.example.weatherbabic.utils.extensions.toKmh
import com.example.weatherbabic.utils.helpers.DeviceLocationHelper
import com.example.weatherbabic.utils.livedata.Resource
import com.example.weatherbabic.utils.livedata.Status
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject
import com.example.weatherbabic.data.database.entities.Location as FavoriteLocation

class WeatherDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: WeatherDetailViewModel by viewModels { viewModelFactory }
    private val forecastAdapter = ForecastAdapter()

    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var deviceLocationObserver: Observer<Location>
    private lateinit var youtubeObserver: Observer<Resource<YoutubeVideo>>

    private var favoriteLocations = mutableListOf<FavoriteLocation>()
    private var youtubeVideo: YoutubeVideo? = null

    private var favoriteLocationSelected = false

    private val weatherCallback = object : WeatherCallback {
        override fun fetchWeather(location: String) {
            val hasInternetConnection = checkHasInternetConnection()
            if (hasInternetConnection) {
                favoriteLocationSelected = true
                viewModel.fetchWeatherDataByName(location)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        binding.forecastRecyclerView.adapter = forecastAdapter
        binding.forecastRecyclerView.setDivider(R.drawable.divider_blue_day)
        binding.backgroundEmpty.visibility = VISIBLE
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (favoriteLocationSelected) return
        checkLocationPermission()
        showLoading()
        observeCurrentWeatherData()
        observeForecastData()
        observeFavoriteLocationsData()
        observeDeviceLocationData()
        setSearchButton()
        observeYoutubeData()
        setYoutubeButton()
    }

    override fun onPause() {
        super.onPause()
        DeviceLocationHelper.deviceLocationData.removeObserver(deviceLocationObserver)
        viewModel.youtubeData.removeObserver(youtubeObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun checkLocationPermission() {
        context?.let {
            if (EasyPermissions.hasPermissions(it, Manifest.permission.ACCESS_FINE_LOCATION)) {
                checkGpsSettings()
            } else {
                EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.need_access_for_location),
                    PERMISSION_CODE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }

    private fun checkGpsSettings() {
        val locationManager =
            context?.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        val gpsEnabled = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false

        if (gpsEnabled) {
            findDeviceLocation()
        } else {
            createDialogNeedGps()
        }
    }

    private fun findDeviceLocation() {
        context?.let {
            DeviceLocationHelper.findDeviceLocation(it)
        }
    }

    private fun createDialogNeedGps() {
        AlertDialog.Builder(context)
            .setTitle(R.string.gps_disabled)
            .setMessage(R.string.need_gps)
            .setPositiveButton(
                getString(R.string.open_settings)
            ) { dialogInterface, _ ->
                dialogInterface.cancel()
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton(R.string.dismiss) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun checkHasInternetConnection(): Boolean {
        val mainActivity = activity as MainActivity
        mainActivity.checkInternetConnection()
        return hasInternetConnection(mainActivity)
    }

    private fun observeDeviceLocationData() {
        deviceLocationObserver = Observer {
            it?.let { location ->
                DeviceLocationHelper.stopDeviceLocationListener()
                viewModel.fetchWeatherDataByLocation(location.latitude, location.longitude)
            }
        }

        DeviceLocationHelper.deviceLocationData.observe(
            viewLifecycleOwner, deviceLocationObserver
        )
    }

    private fun observeCurrentWeatherData() {
        viewModel.weatherData.observe(viewLifecycleOwner,
            Observer<Resource<Weather>> { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        displayCurrentWeatherData(resource.data)
                        hideLoading()
                    }
                    Status.LOADING -> showLoading()
                    Status.ERROR -> {
                        showError()
                        hideLoading()
                    }
                }
            })
    }

    private fun observeForecastData() {
        viewModel.forecastData.observe(viewLifecycleOwner,
            Observer<Resource<List<Forecast>>> { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        displayForecastData(resource.data)
                        hideLoading()
                    }
                    Status.LOADING -> showLoading()
                    Status.ERROR -> {
                        hideLoading()
                    }
                }
            })
    }

    private fun observeFavoriteLocationsData() {
        viewModel.favoriteLocationsData.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoriteLocations.clear()
                favoriteLocations.addAll(it.reversed())
            }
        })
    }

    private fun observeYoutubeData() {
        youtubeObserver = Observer { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    youtubeVideo = resource.data
                    hideLoading()
                }
                Status.LOADING -> showLoading()
                Status.ERROR -> {
                    hideLoading()
                }
            }
        }
        viewModel.youtubeData.observe(
            viewLifecycleOwner, youtubeObserver
        )
    }

    private fun setSearchButton() {
        binding.buttonSearch.setOnClickListener {
            SearchDialogFragment(weatherCallback, favoriteLocations)
                .show(childFragmentManager, WEATHER_APP)
        }
    }

    private fun setYoutubeButton() {
        binding.buttonCheckYoutube.setOnClickListener {
            if (youtubeVideo == null) {
                showError()
            } else {
                val intent = Intent(activity, VideoPlayerActivity::class.java)
                intent.putExtra(ARGUMENT_YOUTUBE_VIDEO, youtubeVideo)
                startActivity(intent)
            }
        }
    }

    private fun displayCurrentWeatherData(weather: Weather?) {
        weather?.let {
            binding.labelDescription.text = weather.description.capitalizeWords()
            binding.labelCurrentTemperature.text = weather.currentTemp.toInt().toString()
            binding.labelTemperatureSymbol.text = getTemperatureSymbol()
            binding.imageWeather.setImageResource(weather.icon)
            binding.labelCityName.text = weather.cityName
            binding.labelFeelsLike.text = formatFeelsLike(context, weather.feelsLike)
            binding.labelSunriseSunset.text =
                formatSunriseSunset(context, weather.sunriseTime, weather.sunsetTime)
            binding.labelWindSpeed.text = formatWindSpeed(weather.windSpeed.toKmh())
            binding.labelHumidity.text = formatPercent(weather.humidity)
            binding.labelPressure.text = formatPressure(weather.pressure)
            binding.groupEmpty.visibility = GONE
            binding.backgroundEmpty.visibility = GONE
            binding.buttonSearch.visibility = VISIBLE
            viewModel.searchYoutube(weather.youtubeQuery)
        }
    }

    private fun displayForecastData(forecastList: List<Forecast>?) {
        forecastList?.let {
            forecastAdapter.addItems(forecastList)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = GONE
    }

    private fun showError() {
        Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
            .show()
    }
}
