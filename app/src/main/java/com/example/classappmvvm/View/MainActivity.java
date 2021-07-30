package com.example.classappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classappmvvm.Adapter.UserAdapter;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.R;
import com.example.classappmvvm.ViewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private MainViewModel viewModel;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        onClicks();
        observe();
    }

    private void observe() {

        viewModel.getUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList=users;
            }
        });

        viewModel.getLoginSuccesState().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer==0){
                    Toast.makeText(getApplicationContext(),"Fill in the fields",Toast.LENGTH_SHORT).show();
                    viewModel.setLoginSuccesState(-1);
                }
                else if(integer==1){
                    startActivity(new Intent(getApplicationContext(),AdminMenuActivity.class));

                    viewModel.setLoginSuccesState(-1);
                }
                else if(integer==3){
                    Toast.makeText(getApplicationContext(),"Login failed.",Toast.LENGTH_SHORT).show();
                    viewModel.setLoginSuccesState(-1);
                }

            }
        });

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user!=null){
                    Intent intent = new Intent(getApplicationContext(),UserActivity.class);
                    intent.putExtra("id",user.getId());
                    intent.putExtra("email",user.getEmail());
                    intent.putExtra("password",user.getPassword());
                    intent.putExtra("firstName",user.getFirstName());
                    intent.putExtra("lastName",user.getLastName());
                    intent.putExtra("course",user.getCourse());
                    intent.putExtra("points",user.getPoints());
                    intent.putExtra("grade",user.getGrade());
                    intent.putExtra("age",user.getAge());
                    startActivity(intent);
                    viewModel.setUser(null);

                }
            }
        });
    }

    private void onClicks() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                viewModel.loginUser(email,password, userList);
            }
        });

    }

    private void setUpViews() {
        emailEditText = findViewById(R.id.emailLogin);
        passwordEditText = findViewById(R.id.passwordLogin);
        loginButton = findViewById(R.id.loginButton);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.init(getApplication());

    }
}