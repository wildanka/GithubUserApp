package com.example.hcsgithubuser.framework

import android.content.Context
import android.net.ConnectivityManager
import com.example.hcsgithubuser.HcsGithubUserApp

object AppUtility {

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = HcsGithubUserApp.instance!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}