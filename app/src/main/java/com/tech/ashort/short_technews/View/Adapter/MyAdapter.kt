package com.tech.ashort.short_technews.View.Adapter

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.tech.ashort.short_technews.Model.Firebase.Database.BookmarkNews
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.ViewModel.MyViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sachin on 17/12/17.
 */
class MyAdapter(var context: Context, var viewModel: ViewModel) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var newsInCard: String = ""
    var newsFromfirebase: String? = (viewModel as MyViewModel).getNewsfromFirebase(context)
    var mFilteredList: List<String>? = newsFromfirebase!!.split("*")
    var date: Date = Date()
    var simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    var selectedNews: String = ""
    var mlistOfsavedNews: List<BookmarkNews> = (viewModel as MyViewModel).newsfromDB


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appTextView: TextView = itemView.findViewById(R.id.news)
        var mBookmark: ImageView? = itemView.findViewById(R.id.bookmark)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        newsInCard = mFilteredList!!.get(position)
        holder.appTextView.setText(newsInCard)

        for (savedNews in mlistOfsavedNews) {
            if (newsInCard.equals(savedNews.news, true)) {
                holder.mBookmark!!.setBackground(context.getResources().getDrawable(android.R.drawable.star_big_on));
            }
        }

        holder.mBookmark!!.setOnClickListener(View.OnClickListener { v: View? ->

            if (holder.mBookmark!!.background!!.constantState ==
                    context.getResources().getDrawable(android.R.drawable.star_big_on).constantState) {
                Toast.makeText(context, "It is already Bookmarked. Kindly remove it from Bookmark section above.", Toast.LENGTH_SHORT)
                        .show()
            } else {
                Toast.makeText(context, "News has been bookmarked !!"
                        , Toast.LENGTH_SHORT).show()
                var bookmarkNews = BookmarkNews()
                selectedNews = holder.appTextView.text.toString()
                bookmarkNews.news = selectedNews
                bookmarkNews.date = simpleDateFormat.format(date.time)
                (viewModel as MyViewModel).storeNews(bookmarkNews)
                updateView(position, holder)
            }
        })
    }

    fun updateView(position: Int, holder: ViewHolder) {
        var strNews = ""
        if (position < mFilteredList!!.size) {
            strNews = mFilteredList!!.get(position)
        }
        if (strNews.equals(selectedNews, true)) {
            holder.mBookmark!!.setBackground(context.getResources().getDrawable(android.R.drawable.star_big_on));
        }
        // notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mFilteredList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list, parent, false)
        return ViewHolder(v)
    }

}