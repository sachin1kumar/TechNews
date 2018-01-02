package com.tech.ashort.short_technews.Model.Firebase

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.support.compat.BuildConfig
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.MainActivity


/**
 * Created by sachin on 10/12/17.
 */
class FirebaseInit {

    private var remoteConfig: FirebaseRemoteConfig? = null
    private var mProgressBar: ProgressDialog?= null
    private var mNews="";
    private var mContext:Context?=null
    private var mCount:Int=0

    fun getNews(context: Context): String {
        mContext=context
        mProgressBar = ProgressDialog(mContext)
        mProgressBar!!.setCancelable(false)
        mProgressBar!!.setMessage("Please wait...")
        mProgressBar!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        val remoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()

        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig!!.setConfigSettings(remoteConfigSettings)
        remoteConfig!!.setDefaults(R.xml.remote_config)

        var cacheExpiration: Long = 0
        if (remoteConfig!!.getInfo().configSettings.isDeveloperModeEnabled) {
            cacheExpiration = 0
        }

        mProgressBar!!.show()

        val handler = Handler()
        remoteConfig!!.activateFetched()
        mNews = remoteConfig!!.getString("newstoday")
        val run = Runnable {
            remoteConfig!!.fetch(cacheExpiration).addOnSuccessListener {
                Log.e("Success", "Fetch Succeeded"+readSharedPref())
                if(readSharedPref()==0){
                    openMainActivity()
                   writeToSharedPref()
                }
                mProgressBar!!.dismiss()
            }
        }
        handler.post(run)
        Log.e("SuccessStr:",remoteConfig!!.getString("newstoday"))
        return mNews
    }


    fun openMainActivity(){
        mProgressBar!!.show()
        val handler = Handler()
        val run = Runnable {
            val `in` = Intent(mContext!!, MainActivity::class.java)
            `in`.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext!!.startActivity(`in`)
        }
        handler.post(run)
    }

    private fun writeToSharedPref() {
        var sharedPreference: SharedPreferences = mContext!!.getSharedPreferences("Count", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putInt("Count",++mCount)
        editor.commit()

    }

    private fun readSharedPref(): Int {
        var sharedPreference: SharedPreferences = mContext!!.getSharedPreferences("Count", Context.MODE_PRIVATE)
        val count = sharedPreference.getInt("Count",0)
        return count
    }

}