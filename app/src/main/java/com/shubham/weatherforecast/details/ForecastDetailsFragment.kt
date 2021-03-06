package com.shubham.weatherforecast.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.shubham.weatherforecast.*

class ForecastDetailsFragment : Fragment() {
    private val args:ForecastDetailsFragmentArgs by navArgs()
    private lateinit var tempDisplaySettingManager:TempDisplaySettingManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val layout=inflater.inflate(R.layout.fragment_forecast_details,container,false)
        tempDisplaySettingManager= TempDisplaySettingManager(requireContext())

        val temptext=layout.findViewById<TextView>(R.id.tempText)
        val descriptiontext=layout.findViewById<TextView>(R.id.descriptionText)
        temptext.text= formatTempForDisplay(args.temp,tempDisplaySettingManager.getTempDisplaySetting())
        descriptiontext.text=args.description
        return layout
    }
}