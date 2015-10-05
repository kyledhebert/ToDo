package com.kylehebert.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by kylehebert on 10/2/15.
 *
 */
public class ToDoListFragment extends Fragment {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mToDoAdapter;

    private LinearLayout mEmptyListLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        mToDoRecyclerView = (RecyclerView) view.findViewById(R.id.to_do_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //this is only visible when the list is empty
        mEmptyListLayout = (LinearLayout) view.findViewById(R.id.empty_list_layout);

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_to_do_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            /*
            Choosing Add To-Do in the menu creates a new To-Do and adds it to the ToDoList.
            Next, an instance of ToDoPagerActivity is started so the user can edit the To-Do.
             */
            case R.id.menu_item_new_to_do:
                ToDo toDo = new ToDo();
                ToDoList.get(getActivity()).addToDo(toDo);
                Intent intent = ToDoPagerActivity.newIntent(getActivity(),toDo.getId());
                startActivity(intent);
                return true;
                //other options can be added here
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void updateUI(){
        ToDoList toDoList = ToDoList.get(getActivity());
        List<ToDo> toDos = toDoList.getToDos();

        //check if a To Do gets modified, and if so update the RecyclerView

        if (mToDoAdapter == null) {
            mToDoAdapter = new ToDoAdapter(toDos);
            mToDoRecyclerView.setAdapter(mToDoAdapter);
        } else {
            mToDoAdapter.notifyDataSetChanged();
        }

        //if the list is empty, hide the recycler view
        if (toDos.size() == 0) {
            mToDoRecyclerView.setVisibility(View.GONE);
        }

    }

    private class ToDoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private CheckBox mCompletedCheckbox;

        private ToDo mToDo;

        public ToDoHolder(View view){
            super(view);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_to_do_title_text_view);
            mCompletedCheckbox = (CheckBox)
                    itemView.findViewById(R.id.list_item_to_do_completed_check_box);

        }

        @Override
        public void onClick(View view) {
            Intent intent = ToDoPagerActivity.newIntent(getActivity(), mToDo.getId());
            startActivity(intent);
        }

        public void bindToDo(ToDo toDo) {

            mToDo = toDo;
            mTitleTextView.setText(mToDo.getTitle());
            mCompletedCheckbox.setChecked(mToDo.isCompleted());
        }


    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder> {

        private List<ToDo> mToDos;

        public ToDoAdapter(List<ToDo> toDos) {
            mToDos = toDos;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_to_do,
                    parent, false);
            return new ToDoHolder(view);
        }

        @Override
        public void onBindViewHolder(ToDoHolder toDoHolder, int position){
            ToDo toDo = mToDos.get(position);
            toDoHolder.bindToDo(toDo);
        }

        @Override
        public int getItemCount(){
            return mToDos.size();
        }

    }





}
