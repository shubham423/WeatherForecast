package com.shubham.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.shubham.weatherforecast.details.ForecastDetailsFragmentArgs
import com.shubham.weatherforecast.forecast.CurrentForecastFragmentDirections
import com.shubham.weatherforecast.locations.LocationEntryFragmentDirections
import androidx.navigation.ui.setupActionBarWithNavController



class MainActivity : AppCompatActivity(),AppNavigator {


    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tempDisplaySettingManager=TempDisplaySettingManager(this)


        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration= AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController,appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.setings_menu,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.tempDisplaySetting->{
                showTempDisplaySettingDialog(this,tempDisplaySettingManager)
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    override fun navigateToCurrentForecast(zipcode: String) {

        val action=LocationEntryFragmentDirections.actionLocationEntryFragmentToCurrentForecastFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToLocationEntry() {

        val action=CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController(R.id.nav_host_fragment).navigate(action)
    }

    override fun navigateToForecastDetails(forecast: DailyForecast) {
        val action=CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp,forecast.description)
        findNavController(R.id.nav_host_fragment).navigate(action)

    }
}



