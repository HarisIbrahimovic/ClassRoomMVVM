package com.example.classappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.classappmvvm.Adapter.ExamAdapter;
import com.example.classappmvvm.Fragments.UpdateUserFragment;
import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.R;
import com.example.classappmvvm.Utils.iActivity;
import com.example.classappmvvm.Utils.iFragment;
import com.example.classappmvvm.ViewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserActivity extends AppCompatActivity implements ExamAdapter.TouchListener, iActivity {
    private FloatingActionButton myProfileButton;
    private RecyclerView recyclerView;
    private ExamAdapter examAdapter;
    private UserViewModel viewModel;
    private List<Exam>examList;
    private User user;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setUpStuff();
        getUser();
        onClicks();
        observe();
    }

    private void getUser() {
        String firstName =getIntent().getStringExtra("firstName");
        String lastName =getIntent().getStringExtra("lastName");
        String course =getIntent().getStringExtra("course");
        String email =getIntent().getStringExtra("email");
        String password =getIntent().getStringExtra("password");
        int id = getIntent().getIntExtra("id",0);
        int points = getIntent().getIntExtra("points",0);
        int grade = getIntent().getIntExtra("grade",0);
        int age = getIntent().getIntExtra("age",0);
        user = new User(id,firstName,lastName,email,password,course,age,grade,points);
    }

    private void observe() {
        viewModel.getExamList().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                examAdapter.setExamList(exams);
                examAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(examAdapter);
                examList= exams;
            }
        });
    }

    private void onClicks() {
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdateUserFragment();
                iFragment updateF = (iFragment) fragment;
                updateF.reciveData(user);
                updateF.setActivity(UserActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.userProfileFrame,fragment).commit();

            }
        });
    }

    private void setUpStuff() {
        examAdapter = new ExamAdapter(getApplicationContext(),this);
        recyclerView = findViewById(R.id.myExamsRecView);
        recyclerView.setAdapter(examAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myProfileButton = findViewById(R.id.userOptions);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.init(getApplication(), getIntent().getStringExtra("course"));
    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.userProfileFrame) != null) {
            getSupportFragmentManager()
                    .beginTransaction().
                    remove(getSupportFragmentManager().findFragmentById(R.id.userProfileFrame)).commit();
        }else finish();
    }


    @Override
    public void onNoteClickes(int position) {
        Exam exam = examList.get(position);
        intent = new Intent(getApplicationContext(),StartExamActivity.class);
        intent.putExtra("examId",exam.getId());
        intent.putExtra("examName",exam.getName());
        DialogExam dialogExam = new DialogExam();
        dialogExam.setUserActivity(UserActivity.this);
        dialogExam.show(getSupportFragmentManager(),"tag");


    }

    public void startExam(){

        int id = getIntent().getIntExtra("id",0);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void getData(int id,String firstName, String lastName, String email, String password, String course, int age,int num) {
        getSupportFragmentManager()
                .beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.CreateUserFrame)).commit();
        viewModel.updateUser(id,firstName,lastName,email,password,course,age,getApplication());
    }
}