package com.tech.ashort.short_technews.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.tech.ashort.short_technews.Model.Firebase.FirebaseInit;

/**
 * Created by sachin on 13/12/17.
 */

public class MyViewModel extends AndroidViewModel{

    private FirebaseInit firebaseInit;

    public MyViewModel(@NonNull Application application) {
        super(application);
        firebaseInit = new FirebaseInit();
    }

    public String getNewsfromFirebase(){
        return firebaseInit.getNews();
    }
}
