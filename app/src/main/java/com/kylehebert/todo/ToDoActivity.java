package com.kylehebert.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by kylehebert on 10/2/15.
 * Hosts a single ToDoFragment
 */
public class ToDoActivity extends SingleFragmentActivity {

    private static final String EXTRA_TO_DO_ID = "com.kylehebert.todo.to_do_id";

    public static Intent newIntent(Context packageContext, UUID toDoId) {
        Intent intent = new Intent(packageContext, ToDoActivity.class);
        intent.putExtra(EXTRA_TO_DO_ID, toDoId);
        return intent;

    }

    @Override
    protected Fragment createFragment(){
        UUID toDoId = (UUID) getIntent().getSerializableExtra(EXTRA_TO_DO_ID);
        return  ToDoFragment.newInstance(toDoId);
    }


}
