package com.example.weatherbabic.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.weatherbabic.R
import com.example.weatherbabic.utils.hasInternetConnection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        checkInternetConnection()
    }

    fun checkInternetConnection() {
        if (hasInternetConnection(this)) {
            connectionBackground.visibility = View.GONE
            connectionText.visibility = View.GONE
        } else {
            connectionBackground.visibility = View.VISIBLE
            connectionText.visibility = View.VISIBLE
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
