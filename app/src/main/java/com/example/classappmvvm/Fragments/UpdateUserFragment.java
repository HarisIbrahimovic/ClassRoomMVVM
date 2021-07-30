package com.example.classappmvvm.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.R;
import com.example.classappmvvm.Utils.iActivity;
import com.example.classappmvvm.Utils.iFragment;


public class UpdateUserFragment extends Fragment implements iFragment {

    private iActivity activity;
    private EditText updateFirstName;
    private EditText updateLastName;
    private EditText updateEmail;
    private EditText updatePassword;
    private EditText updateAge;
    private EditText updateCourse;
    private Button updateButton;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_update_user, container, false);
        setUpViews(view);
        if (savedInstanceState != null) setUpUserSavedInstance(savedInstanceState);
        else setUpUser();
        onClicks();
        return view;
    }

    private void onClicks() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void sendData() {
        String firstName = updateFirstName.getText().toString();
        String lastName = updateLastName.getText().toString();
        String course = updateCourse.getText().toString();
        String email = updateEmail.getText().toString();
        String password = updatePassword.getText().toString();
        int id = user.getId();
        int age = Integer.parseInt(updateAge.getText().toString());
        if(TextUtils.isEmpty(firstName)||TextUtils.isEmpty(lastName)||TextUtils.isEmpty(course)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            Toast.makeText(getActivity().getApplicationContext(),"Fill in the fields",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<8){
            Toast.makeText(getActivity().getApplicationContext(), "Password too short.", Toast.LENGTH_SHORT).show();
            return;
        }
        activity.getData(id,firstName,lastName,email,password,course,age,1);
    }



    private void setUpUserSavedInstance(Bundle savedInstanceState) {
        String firstName = savedInstanceState.getString("firstName");
        String lastName = savedInstanceState.getString("lastName");
        String course = savedInstanceState.getString("course");
        String email = savedInstanceState.getString("email");
        String password = savedInstanceState.getString("password");
        int id = savedInstanceState.getInt("id");
        int age = savedInstanceState.getInt("age");

        user = new User(id,firstName,lastName,email,password,course,age,0,0);

    }

    private void setUpUser() {
        updateFirstName.setText(user.getFirstName());
        updateLastName.setText(user.getLastName());
        updateEmail.setText(user.getEmail());
        updatePassword.setText(user.getPassword());
        updateCourse.setText(user.getCourse());
        updateAge.setText(user.getAge()+"");
    }

    private void setUpViews(View view) {
        updateFirstName = view.findViewById(R.id.updateFirstName);
        updateLastName = view.findViewById(R.id.updateLastName);
        updateEmail = view.findViewById(R.id.updateEmail);
        updatePassword = view.findViewById(R.id.updatePassword);
        updateAge = view.findViewById(R.id.updateAge);
        updateCourse = view.findViewById(R.id.updateCourse);
        updateButton = view.findViewById(R.id.updateUserButton);
    }

    @Override
    public void setActivity(iActivity activity) {
        this.activity=activity;
    }

    @Override
    public void reciveData(User user) {
        this.user = user;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstName",user.getFirstName());
        outState.putString("lastName",user.getLastName());
        outState.putString("course",user.getCourse());
        outState.putString("email",user.getEmail());
        outState.putString("password",user.getPassword());
        outState.putInt("age",user.getAge());
        outState.putInt("id",user.getId());
    }
}