package com.kylehebert.todo;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kylehebert on 9/28/15. Objects of this class will
 * represent an individual task on the user's list
 */
public class ToDoItem {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mCompleted;

    public ToDoItem(){
        //each task gets a unique ID
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
