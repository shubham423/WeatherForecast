package com.shubham.weatherforecast

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting) : String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenhiet -> String.format("%.2f F°", temp)
        TempDisplaySetting.celsius -> {
            val temp = (temp - 32f) * (5f / 9f)
            String.format("%.2f C°", temp)
        }
    }
}

     fun showTempDisplaySettingDialog(context: Context,tempDisplaySettingManager: TempDisplaySettingManager) {
        val dialogBuilder = AlertDialog.Builder(context)
            .setTitle("Choose Display Units")
            .setMessage("chose which temperature units to display")
            .setPositiveButton("F") { _, _ ->

                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenhiet)
            }
            .setNeutralButton("C") { _, _ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.celsius)
            }
            .setOnDismissListener {
                Toast.makeText(
                    context,
                    "settings effect will take place after restart",
                    Toast.LENGTH_SHORT
                ).show()
            }
        dialogBuilder.show()
    }
