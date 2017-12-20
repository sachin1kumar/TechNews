package com.tech.ashort.short_technews.View.Adapter

import android.arch.lifecycle.ViewModel
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tech.ashort.short_technews.R
import android.content.Context
import android.widget.ImageView

/**
 * Created by sachin on 17/12/17.
 */
class MyAdapter(var listData: MutableList<String>, var context: Context,var viewModel: ViewModel) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>()
{

    val TAG: String = "MyAdapter"
    var news: String = ""
    var mFilteredList: List<String>? = listData
    var mviewModel: ViewModel? = viewModel


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appTextView: TextView = itemView.findViewById(R.id.news)
        var mBookmark: ImageView? = itemView.findViewById(R.id.bookmark)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news = mFilteredList!!.get(position)
        holder.appTextView.setText(news)

        holder.mBookmark!!.setOnClickListener(View.OnClickListener { v: View? ->
            if ()
        })
    }

    override fun getItemCount(): Int {
        return mFilteredList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list, parent, false)
        return ViewHolder(v)
    }

}