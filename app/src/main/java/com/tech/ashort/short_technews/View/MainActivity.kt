package com.tech.ashort.short_technews.View

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.Adapter.MyAdapter
import com.tech.ashort.short_technews.ViewModel.MyViewModel


class MainActivity : AppCompatActivity(){

    private var viewModel: ViewModel? = null
    private var mToolbar: Toolbar? = null
    private var adapter: MyAdapter? = null
    private var context: Context? = null
    private var mTabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mTabLayout = findViewById(R.id.tabs)
        mToolbar!!.setTitle(R.string.app_name)

        setHeadingOnTab()

        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        context = this
        adapter = MyAdapter(context as MainActivity, viewModel as ViewModel)

        var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        var bookmarkFrag = BookMarkFragment()
        bookmarkFrag.setData(context as MainActivity, viewModel as ViewModel)

        return if (id == R.id.action_bookmark) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame,bookmarkFrag,"bookmark")
                    .addToBackStack(null)
                    .commit()
            return true
        }
        else
            super.onOptionsItemSelected(item)

    }

    private fun setHeadingOnTab() {
        mTabLayout!!.addTab(mTabLayout!!.newTab().setText("Today's Latest Tech News"))
    }


}
