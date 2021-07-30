package com.example.classappmvvm.Fragments;

import android.os.Bundle;

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


public class newUserFragment extends Fragment implements iFragment {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private EditText courseEditText;
    private Button createNewUserButton;
    private iActivity activity;
    private String fName,lName,sAge,sPass,sEmail,sCourse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);
        setUpViews(view);
        onClicks();
        return view;
    }

    private void onClicks() {
        createNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValues();
            }
        });
    }

    private void checkValues() {
        fName = firstNameEditText.getText().toString().trim();
        lName = lastNameEditText.getText().toString().trim();
        sAge = ageEditText.getText().toString().trim();
        sEmail = emailEditText.getText().toString().trim();
        sPass = passEditText.getText().toString().trim();
        sCourse = courseEditText.getText().toString().trim();
        if(TextUtils.isEmpty(fName)||TextUtils.isEmpty(lName)||TextUtils.isEmpty(sAge)||TextUtils.isEmpty(sEmail)||TextUtils.isEmpty(sPass)||TextUtils.isEmpty(sCourse)){
            Toast.makeText(getActivity().getApplicationContext(), "Fill in the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sPass.length()<8){
            Toast.makeText(getActivity().getApplicationContext(), "Password too short.", Toast.LENGTH_SHORT).show();
            return;
        }
        int numAge = Integer.parseInt(sAge);
        activity.getData(-1,fName,lName,sEmail,sPass,sCourse,numAge,0);

    }


    private void setUpViews(View view) {
        firstNameEditText = view.findViewById(R.id.newUserFirstName);
        lastNameEditText = view.findViewById(R.id.newUserLastName);
        ageEditText = view.findViewById(R.id.newUserAge);
        emailEditText = view.findViewById(R.id.newUserEmail);
        passEditText = view.findViewById(R.id.newUserPass);
        courseEditText = view.findViewById(R.id.newUserCourse);
        createNewUserButton = view.findViewById(R.id.createNewUserFB);
    }


    @Override
    public void setActivity(iActivity Activity) {
        this.activity=Activity;
    }

    @Override
    public void reciveData(User user) {

    }
}