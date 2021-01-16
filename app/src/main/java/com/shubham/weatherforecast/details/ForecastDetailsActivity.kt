package com.shubham.weatherforecast.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.shubham.weatherforecast.*

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager:TempDisplaySettingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)
        setTitle(R.string.forecast_details)

        tempDisplaySettingManager= TempDisplaySettingManager(this)

        val tempText=findViewById<TextView>(R.id.tempText)
        val descriptionText=findViewById<TextView>(R.id.descriptionText)

        val temp=intent.getFloatExtra("key_temp",0f)
        tempText.text= formatTempForDisplay(temp,tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text=intent.getStringExtra("key_description")
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

}