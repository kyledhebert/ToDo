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

    public void addToDo(ToDo toDo) {
        mToDos.add(toDo);
    }

    public void removeToDo(ToDo toDo) {
        mToDos.remove(toDo);
    }

    private List<ToDo> mToDos;

    public static ToDoList get (Context context) {
        if (sToDoList == null){
            sToDoList = new ToDoList(context);
        }
        return sToDoList;
    }


    private ToDoList(Context context){
        mToDos = new ArrayList<>();
    }

    public List<ToDo> getToDos(){
        return mToDos;
    }

    public ToDo getToDo(UUID id){
        for (ToDo toDo : mToDos){
            if (toDo.getId().equals(id)) {
                return toDo;
            }
        }
        return null;
    }





}
