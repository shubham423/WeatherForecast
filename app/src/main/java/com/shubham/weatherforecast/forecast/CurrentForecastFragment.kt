package com.shubham.weatherforecast.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shubham.weatherforecast.*
import com.shubham.weatherforecast.details.ForecastDetailsActivity


class CurrentForecastFragment : Fragment() {


    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator=context as AppNavigator
    }

    private lateinit var tempDisplaySettingManager:TempDisplaySettingManager
    private val forecastRepository=ForecastRepository()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val zipcode=arguments!!.getString(KEY_ZIPCODE)?:""
        tempDisplaySettingManager= TempDisplaySettingManager(requireContext())
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val locationEntryBtn:FloatingActionButton=view.findViewById(R.id.locationEntryBtn)

        locationEntryBtn.setOnClickListener{
            appNavigator.navigateToLocationEntry()
        }

        val forecastList: RecyclerView =view.findViewById(R.id.forecastList)
        forecastList.layoutManager= LinearLayoutManager(requireContext())
        val dailyForecastAdapter= DailyForecastListAdapter({
            showForecastDetails(it)
        },tempDisplaySettingManager)
        forecastList.adapter=dailyForecastAdapter

        // Create the observer which updates the UI in response to forecast updates
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            // update our list adapter
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
        forecastRepository.loadForecast(zipcode)
        return view
    }

    private fun showForecastDetails(forecast:DailyForecast) {
        val forcastDetailsIntent= Intent(requireContext(), ForecastDetailsActivity::class.java)
        forcastDetailsIntent.putExtra("key_temp",forecast.temp)
        forcastDetailsIntent.putExtra("key_description",forecast.description)
        startActivity(forcastDetailsIntent)
    }

    companion object{
        const val KEY_ZIPCODE="key_zipcode"
        fun newInstance(zipcode:String):CurrentForecastFragment{
            val fragment=CurrentForecastFragment()

            val args= Bundle()
            args.putString(KEY_ZIPCODE,zipcode)
            fragment.arguments=args
            return fragment

        }
    }


}