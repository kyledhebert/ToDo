package com.kylehebert.todo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by kylehebert on 10/3/15. Allows the user to
 * page through the list of to dos by swiping left and right.
 */
public class ToDoPagerActivity extends FragmentActivity {

    private static final String EXTRA_TO_DO_ID = "com.kylehebert.todo.to_do_id";

    private ViewPager mViewPager;
    private List<ToDo> mToDos;

    public static Intent newIntent(Context packageContext, UUID toDoId) {
        Intent intent = new Intent(packageContext, ToDoPagerActivity.class);
        intent.putExtra(EXTRA_TO_DO_ID, toDoId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_pager);

        UUID toDoId = (UUID) getIntent().getSerializableExtra(EXTRA_TO_DO_ID);

        mViewPager = (ViewPager)findViewById(R.id.activity_to_do_pager_view_pager);

        mToDos = ToDoList.get(this).getToDos();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            /*
            by getting the count--or number of items in the list of ToDos-- the ViewPager
            can then reference a To-Do by its ID and return a ToDoFragment for the To-Do
            when the user swipes left and right
             */
            @Override
            public Fragment getItem(int position) {
                ToDo toDo = mToDos.get(position);
                return ToDoFragment.newInstance(toDo.getId());
            }

            @Override
            public int getCount() {
                return mToDos.size();
            }
        });
    }
}
