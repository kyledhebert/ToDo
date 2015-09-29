package com.kylehebert.todo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kylehebert on 9/28/15. Object that
 * will hold all of the users ToDoItems
 */
public class ToDoList {

    private static ToDoList sToDoList;

    private List<ToDoItem> mToDoItems;

    public static ToDoList get (Context context) {
        if (sToDoList == null){
            sToDoList = new ToDoList(context);
        }
        return sToDoList;
    }

    private ToDoList(Context context){
        mToDoItems = new ArrayList<>();
    }

    public List<ToDoItem> getToDoItems(){
        return mToDoItems;
    }

    public ToDoItem getToDo(UUID id){
        for (ToDoItem toDoItem : mToDoItems){
            if (toDoItem.getId().equals(id)) {
                return toDoItem;
            }
        }
        return null;
    }




}
