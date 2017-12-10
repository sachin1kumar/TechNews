package com.tech.ashort.short_technews.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.TextView
import com.tech.ashort.short_technews.Model.Firebase.FirebaseInit
import com.tech.ashort.short_technews.R

class MainActivity : AppCompatActivity() {

    private var mnewsView:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mnewsView = findViewById(R.id.news)
        mnewsView!!.setText(FirebaseInit.initFirebaseAndgetNews())
    }
}
