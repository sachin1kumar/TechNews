package com.tech.ashort.short_technews.Model.Firebase.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tech.ashort.short_technews.Model.Firebase.Database.AppDatabase.Companion.INSTANCE

/**
 * Created by sachin on 18/12/17.
 */

@Database(entities = arrayOf(BookmarkNews::class), version = 3, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {

    public abstract fun NewsModel(): NewsDao

    companion object {

        private var INSTANCE:AppDatabase?=null

        fun getDatabase(context: Context) : AppDatabase{
            if (INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java,"NewsDB")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE as AppDatabase
        }

        fun destroyInstance(){
            INSTANCE=null
        }

    }

}