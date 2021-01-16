package com.shubham.weatherforecast

interface AppNavigator {
    fun navigateToCurrentForecast(zipcode:String)
    fun navigateToLocationEntry()
}