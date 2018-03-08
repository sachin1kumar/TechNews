package com.tech.ashort.short_technews.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
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
    private MutableLiveData<String> mCurrentNews;
    private MutableLiveData<List<BookmarkNews>> mMutRef;


    public MyViewModel(@NonNull Application application){
        super(application);
        firebaseInit = new FirebaseInit();
        mappDatabase = AppDatabase.Companion.getDatabase(application);
    }

    public MutableLiveData<String> getNewsfromFirebase(Context context){
        if (mCurrentNews==null){
            mCurrentNews = new MutableLiveData<>();
        }
        mCurrentNews.setValue(firebaseInit.getNews(context));
        return mCurrentNews;
    }

    public MutableLiveData<List<BookmarkNews>> getNewsfromDB(){
        MutableLiveData<List<BookmarkNews>> refLive = getMutableRef();
        refLive.setValue(mappDatabase.NewsModel().getNews());
        return refLive;
    }

    public MutableLiveData<List<BookmarkNews>> getSortedNewsfromDB(){
        MutableLiveData<List<BookmarkNews>> refLive = getMutableRef();
        refLive.setValue(mappDatabase.NewsModel().getSortedNews());
        return refLive;
    }

    public void storeNews(BookmarkNews bookmarkNews){
        mappDatabase.NewsModel().insertNews(bookmarkNews);
    }

    public void deleteNews(String news){
        mappDatabase.NewsModel().deleteNews(news);
    }

    private MutableLiveData<List<BookmarkNews>> getMutableRef(){
        if (mMutRef==null){
            mMutRef = new MutableLiveData<>();
        }
        return mMutRef;
    }
}
