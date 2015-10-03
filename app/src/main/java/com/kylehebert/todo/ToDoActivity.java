package com.kylehebert.todo;

import android.support.v4.app.Fragment;

/**
 * Created by kylehebert on 10/2/15.
 * Hosts a single ToDoFragment
 */
public class ToDoActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ToDoListFragment();
    }


}
