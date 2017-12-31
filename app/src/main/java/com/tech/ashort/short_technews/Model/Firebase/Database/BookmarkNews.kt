package com.tech.ashort.short_technews.Model.Firebase.Database

/**
 * Created by sachin on 18/12/17.
 */
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import org.jetbrains.annotations.NotNull

@Entity
class BookmarkNews {
    @PrimaryKey
    var news:String=""
    var date:String=""
}