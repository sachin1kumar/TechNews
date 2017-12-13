package com.tech.ashort.short_technews.View

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.widget.TextView
import com.tech.ashort.short_technews.Model.Firebase.FirebaseInit
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.ViewModel.MyViewModel

class MainActivity : AppCompatActivity() {

    private var mnewsView:TextView? = null
    private var viewModel: ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mnewsView = findViewById(R.id.news)
        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        mnewsView!!.setText((viewModel as MyViewModel).getNewsfromFirebase())
    }
}
