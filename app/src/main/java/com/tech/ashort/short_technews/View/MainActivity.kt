package com.tech.ashort.short_technews.View

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.tech.ashort.short_technews.Model.Firebase.Database.BookmarkNews
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.Adapter.MyAdapter
import com.tech.ashort.short_technews.ViewModel.MyViewModel

class MainActivity : AppCompatActivity(){

    private var viewModel: ViewModel? = null
    private var mToolbar: Toolbar? = null
    private var adapter: MyAdapter? = null
    private var context: Context? = null
    private var mTabLayout: TabLayout? = null
    private var mNewsInStr: String? = ""
    private var mlistOfsavedNews: List<BookmarkNews>? =null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mTabLayout = findViewById(R.id.tabs)
        mToolbar!!.setTitle(R.string.app_name)

        setHeadingOnTab()

        recyclerView = findViewById(R.id.recyclerView)
        context = this


        (viewModel as MyViewModel).getNewsfromFirebase(context).observe(this,android.arch.lifecycle.Observer{
             mNewsInStr = it
             //Log.e("LiveData:Observer:",mNewsInStr)
             doDboperation()
        })

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

    override fun onRestart() {
        super.onRestart()
        openMainActivity()
    }

    fun openMainActivity(){
        val handler = Handler()
        val run = Runnable {
            val `in` = Intent(this, MainActivity::class.java)
            `in`.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(`in`)
        }
        handler.post(run)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    fun doDboperation(){
        (viewModel as MyViewModel).getNewsfromDB().observe(this,android.arch.lifecycle.Observer{
            mlistOfsavedNews = it
            //Log.e("LiveData:Observer:DB",mNewsInStr)
            adapter = MyAdapter(context as MainActivity, viewModel as ViewModel)
            adapter!!.setNews(mNewsInStr)
            adapter!!.setNewsFromDB(mlistOfsavedNews)

            var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
            recyclerView!!.layoutManager = mLayoutManager
            recyclerView!!.itemAnimator
            recyclerView!!.isNestedScrollingEnabled = false
            recyclerView!!.adapter = adapter
        })
    }
}
