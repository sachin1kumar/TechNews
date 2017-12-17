package com.tech.ashort.short_technews.View

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.tech.ashort.short_technews.Model.Firebase.FirebaseInit
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.Adapter.MyAdapter
import com.tech.ashort.short_technews.ViewModel.MyViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainActivity : AppCompatActivity() {

    private var viewModel: ViewModel? = null
    private var mToolbar: Toolbar? = null
    private var mNews:String = ""
    private var listOfNews:List<String>? = null
    private var adapter: MyAdapter? = null
    private var context: Context? = null
    private var mTabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mToolbar = findViewById(R.id.toolbar)
        mTabLayout = findViewById(R.id.tabs)
        mToolbar!!.setTitle(R.string.app_name)

        setHeadingOnTab()

        mNews = (viewModel as MyViewModel).getNewsfromFirebase()
        listOfNews = mNews.split("*")

        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        context = this
        adapter = MyAdapter(listOfNews as ArrayList<String>, context as MainActivity)

        var mLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator
        recyclerView.isNestedScrollingEnabled=false
        recyclerView.adapter = adapter
    }

    private fun setHeadingOnTab() {
        mTabLayout!!.addTab(mTabLayout!!.newTab().setText("Today's Latest Tech News"))
    }
}
