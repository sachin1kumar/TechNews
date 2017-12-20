package com.tech.ashort.short_technews.Model.Firebase.Database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query

/**
 * Created by sachin on 18/12/17.
 */

@Dao
interface NewsDao {

    @Query("SELECT * FROM BookmarkNews ORDER BY newsId Desc")
    fun getNews() : LiveData<List<BookmarkNews>>

    @Insert(onConflict = IGNORE)
    fun insertNews(bookmarkNews: BookmarkNews)
}