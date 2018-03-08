package com.tech.ashort.short_technews.View.Adapter

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tech.ashort.short_technews.Model.Firebase.Database.BookmarkNews
import com.tech.ashort.short_technews.R
import com.tech.ashort.short_technews.View.CustomDialog
import com.tech.ashort.short_technews.ViewModel.MyViewModel
import java.util.*

/**
 * Created by sachin on 31/12/17.
 */
class BookMarkAdapter(var context: Context, var viewModel: ViewModel) :
        RecyclerView.Adapter<BookMarkAdapter.BookMarkViewHolder>() {
    var savedOn: String = "Saved On : "
    var mFilteredList: ArrayList<BookmarkNews>?= null

    fun setNewsFromDB(mlistOfNews: ArrayList<BookmarkNews>?) {
        mFilteredList = mlistOfNews
    }

    class BookMarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsTextView: TextView? = itemView.findViewById(R.id.news)
        var delete: ImageView? = itemView.findViewById(R.id.delete)
        var date: TextView? = itemView.findViewById(R.id.date)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        holder.newsTextView!!.setText(mFilteredList!!.get(position).news)
        holder.date!!.setText(savedOn+ mFilteredList!!.get(position).date)

        holder.delete!!.setOnClickListener(View.OnClickListener { v: View? ->
            (viewModel as MyViewModel).deleteNews(holder.newsTextView!!.text.toString())
            if (mFilteredList!!.size >0)
                mFilteredList!!.removeAt(position)
            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int {
        if(mFilteredList!!.size == 0){
            //Show Custom Dialog
            CustomDialog(context).show()
        }
        return mFilteredList!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookMarkAdapter.BookMarkViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.bookmark_list, parent, false)
        return BookMarkViewHolder(v)
    }

}