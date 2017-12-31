package com.tech.ashort.short_technews.View

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.Adapter.BookMarkAdapter
import android.support.v7.app.AppCompatActivity

/**
 * Created by sachin on 31/12/17.
 */
class BookMarkFragment : Fragment() {

    @get:JvmName("getContext_")
    private var view: View? = null
    private var mContext: Context? = null
    private var mViewModel: ViewModel? = null
    private var mAdapter: BookMarkAdapter? = null
    private var mToolbar: Toolbar? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu!!.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater!!.inflate(R.layout.bookmark_main, container, false)
        mToolbar = view!!.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(mToolbar)
        mToolbar!!.setTitle("Bookmark")
        mToolbar!!.navigationIcon = mContext!!.resources.getDrawable(R.drawable.abc_ic_ab_back_material)
        mToolbar!!.setNavigationOnClickListener(View.OnClickListener { v: View? ->
            //do it on background thread....
            startActivity(Intent(mContext!!,MainActivity::class.java))
        })

        var recyclerView: RecyclerView = view!!.findViewById(R.id.bookmarkrecView)
        mAdapter = BookMarkAdapter(mContext!!, mViewModel!!)

        var mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = mAdapter

        return view
    }

    override fun onResume() {
        super.onResume()
        getView()!!.isFocusableInTouchMode=true
        getView()!!.requestFocus()
        getView()!!.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                return if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    //do it on background thread....

                    startActivity(Intent(mContext!!,MainActivity::class.java))
                    true
                } else false
            }
        })
    }

    fun setData(context: Context?, viewModel: ViewModel) {
        mContext = context
        mViewModel = viewModel
    }
}