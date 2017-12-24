package com.tech.ashort.short_technews.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.tech.ashort.short_technews.Model.Firebase.Database.AppDatabase;
import com.tech.ashort.short_technews.Model.Firebase.Database.BookmarkNews;
import com.tech.ashort.short_technews.Model.Firebase.FirebaseInit;

import java.util.List;

/**
 * Created by sachin on 13/12/17.
 */

public class MyViewModel extends AndroidViewModel {

    private FirebaseInit firebaseInit;
    private AppDatabase mappDatabase;
    public List<BookmarkNews> savedNews;

    public MyViewModel(@NonNull Application application) {
        super(application);
        firebaseInit = new FirebaseInit();
        mappDatabase = AppDatabase.Companion.getDatabase(application);
    }

    public String getNewsfromFirebase() {
        return firebaseInit.getNews();
    }

  /*  public AppDatabase getDatabaseInstance() {
        return mappDatabase;
    }*/

    public List<BookmarkNews> getNewsfromDB() {
        savedNews = mappDatabase.NewsModel().getNews();
        return savedNews;
    }

    public void storeNews(BookmarkNews bookmarkNews){
        mappDatabase.NewsModel().insertNews(bookmarkNews);
    }

}
