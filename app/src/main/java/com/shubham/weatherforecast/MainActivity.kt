package com.shubham.weatherforecast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.weatherforecast.details.ForecastDetailsActivity
import com.shubham.weatherforecast.forecast.CurrentForecastFragment
import com.shubham.weatherforecast.locations.LocationEntryFragment

class MainActivity : AppCompatActivity(),AppNavigator {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tempDisplaySettingManager=TempDisplaySettingManager(this)


       supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,LocationEntryFragment())
           .commit()
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
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,CurrentForecastFragment.newInstance(zipcode)).commit()
    }

    override fun navigateToLocationEntry() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,LocationEntryFragment()).commit()
    }

}