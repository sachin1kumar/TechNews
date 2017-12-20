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

    @NotNull
    @PrimaryKey
    public var newsId:Int=0

    public var news:String=""

}