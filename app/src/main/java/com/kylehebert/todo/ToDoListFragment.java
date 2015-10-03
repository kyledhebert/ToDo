package com.kylehebert.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by kylehebert on 10/2/15.
 *
 */
public class ToDoListFragment extends Fragment {

    private RecyclerView mToDoRecyclerView;
    private ToDoAdapter mToDoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        mToDoRecyclerView = (RecyclerView) view.findViewById(R.id.to_do_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        ToDoList toDoList = ToDoList.get(getActivity());
        List<ToDo> toDos = toDoList.getToDos();

        mToDoAdapter = new ToDoAdapter(toDos);
        mToDoRecyclerView.setAdapter(mToDoAdapter);
    }

    private class ToDoHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;

        public ToDoHolder(View view){
            super(view);

            mTitleTextView = (TextView) view;

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
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            return new ToDoHolder(view);
        }

        @Override
        public void onBindViewHolder(ToDoHolder toDoHolder, int position){
            ToDo toDo = mToDos.get(position);
            toDoHolder.mTitleTextView.setText(toDo.getTitle());
        }

        @Override
        public int getItemCount(){
            return mToDos.size();
        }

    }



}
