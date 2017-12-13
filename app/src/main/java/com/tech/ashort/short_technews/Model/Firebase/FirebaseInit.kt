package com.tech.ashort.short_technews.Model.Firebase

import android.support.compat.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tech.ashort.short_technews.R
import android.R.attr.button
import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener

/**
 * Created by sachin on 10/12/17.
 */
class FirebaseInit {

    private var remoteConfig: FirebaseRemoteConfig? = null

    init {
        val remoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()

        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig!!.setConfigSettings(remoteConfigSettings);
        remoteConfig!!.setDefaults(R.xml.remote_config);
    }

    fun getNews(): String {
        var cacheExpiration: Long = 0
        if (remoteConfig!!.getInfo().configSettings.isDeveloperModeEnabled) {
            cacheExpiration = 0
        }

        remoteConfig!!.fetch(0).addOnSuccessListener {
            Log.e("Success", "Fetch Succeeded")
            remoteConfig!!.activateFetched()
        }

        return remoteConfig!!.getString("newstoday")
    }
}