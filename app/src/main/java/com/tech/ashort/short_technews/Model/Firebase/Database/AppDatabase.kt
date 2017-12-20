package com.tech.ashort.short_technews.Model.Firebase.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tech.ashort.short_technews.Model.Firebase.Database.AppDatabase.Companion.INSTANCE

/**
 * Created by sachin on 18/12/17.
 */

@Database(entities = arrayOf(BookmarkNews::class), version = 1, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE:AppDatabase?=null
    }

    public abstract fun getNewsData(): NewsDao

    init {
         fun getDatabase(context: Context) : AppDatabase{
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java,"NewsDB")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as AppDatabase
         }
    }

    init {
        fun destroyInstance(){
            INSTANCE=null
        }
    }


}