package com.shubham.weatherforecast

import android.content.Context


enum class TempDisplaySetting{
    Fahrenhiet , celsius
}

class TempDisplaySettingManager(context: Context) {
    val preferences =context.getSharedPreferences("settings",Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting){
        preferences.edit().putString("key_temp_display",setting.name).commit()
    }

    fun getTempDisplaySetting() : TempDisplaySetting{
        val settingValue=preferences.getString("key_temp_display",TempDisplaySetting.Fahrenhiet.name)?:TempDisplaySetting.Fahrenhiet.name
        return TempDisplaySetting.valueOf(settingValue)
    }

}