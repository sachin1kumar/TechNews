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
import android.widget.Toast
import com.tech.ashort.short_technews.Model.Firebase.Database.AppDatabase
import com.tech.ashort.short_technews.Model.Firebase.Database.BookmarkNews
import com.tech.ashort.short_technews.ViewModel.MyViewModel

/**
 * Created by sachin on 17/12/17.
 */
class MyAdapter(var context: Context,var viewModel: ViewModel) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>()
{

    val TAG: String = "MyAdapter"
    var newsInCard: String = ""
    var newsFromDB: String = ""
    var newsFromfirebase: String? = (viewModel as MyViewModel).getNewsfromFirebase()
    var mFilteredList: List<String>? = newsFromfirebase!!.split("*")
    var mlistOfsavedNews :List<BookmarkNews> = (viewModel as MyViewModel).newsfromDB

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appTextView: TextView = itemView.findViewById(R.id.news)
        var mBookmark: ImageView? = itemView.findViewById(R.id.bookmark)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        newsInCard = mFilteredList!!.get(position)
        holder.appTextView.setText(newsInCard)

        for (savedNews in mlistOfsavedNews){
           if(newsInCard.equals(savedNews.news,true)){
               var sdk: Int = android.os.Build.VERSION.SDK_INT;
               if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                   holder.mBookmark!!.setBackgroundDrawable(context.getResources().getDrawable(android.R.drawable.star_big_on));
               } else {
                   holder.mBookmark!!.setBackground(context.getResources().getDrawable(android.R.drawable.star_big_on));
               }
           }
        }

        holder.mBookmark!!.setOnClickListener(View.OnClickListener { v: View? ->
            Toast.makeText(context,"Star Clicked",Toast.LENGTH_SHORT).show()
            var bookmarkNews = BookmarkNews()
            bookmarkNews.news=newsInCard
            (viewModel as MyViewModel).storeNews(bookmarkNews)
            notifyDataSetChanged()
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