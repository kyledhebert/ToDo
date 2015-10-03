package com.kylehebert.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by kylehebert on 10/2/15. A fragment for displaying the details
 * of a single to do.
 */
public class ToDoFragment extends Fragment {

    private ToDo mToDo;
    private EditText mTitleField;
    private CheckBox mCompletedCheckBox;

    private static final String ARG_TO_DO_ID = "com.kylehebert.todo.to_do_id";


    /*
    ToDoActivity will call newInstance when it needs to create a ToDoFragment
     */
    public static ToDoFragment newInstance(UUID toDoId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TO_DO_ID, toDoId);

        ToDoFragment toDoFragment = new ToDoFragment();
        toDoFragment.setArguments(args);
        return toDoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get the To Do based on UUID
        UUID toDoId = (UUID) getArguments().getSerializable(ARG_TO_DO_ID);
        mToDo = ToDoList.get(getActivity()).getToDo(toDoId);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_to_do, container, false);

        mTitleField = (EditText)view.findViewById(R.id.to_do_title);
        mTitleField.setText(mToDo.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if text changes, set the title to the new text
                mToDo.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCompletedCheckBox = (CheckBox)view.findViewById(R.id.to_do_complete);
        mCompletedCheckBox.setChecked(mToDo.isCompleted());
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //when checked set the to do's completed property
                mToDo.setCompleted(isChecked);
            }
        });

        return view;
    }

}
