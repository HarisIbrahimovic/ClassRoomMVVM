package com.example.classappmvvm.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.classappmvvm.Adapter.UserAdapter;
import com.example.classappmvvm.Fragments.UpdateUserFragment;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.R;
import com.example.classappmvvm.Utils.iActivity;
import com.example.classappmvvm.Utils.iFragment;
import com.example.classappmvvm.ViewModel.AdminViewModel;
import com.example.classappmvvm.Fragments.newUserFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;

public class AdminMenuActivity extends AppCompatActivity implements iActivity ,UserAdapter.TouchListener{
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private AdminViewModel viewModel;
    private FloatingActionButton addUserButton;
    private FloatingActionButton addExamButton;
    private List<User> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        setUpStuff();
        onClicks();
        observe();
    }

    private void onClicks() {
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new newUserFragment();
                iFragment newUserF = (iFragment) fragment;
                newUserF.setActivity(AdminMenuActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.CreateUserFrame,fragment).commit();
            }
        });
        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String randomString = UUID.randomUUID().toString();
                Intent intent = new Intent(getApplicationContext(), CreateExamActivity.class);
                intent.putExtra("examId",randomString);
                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(getApplicationContext(),"User removed.",Toast.LENGTH_SHORT).show();
                viewModel.deleteUser(userAdapter.getUserAt(viewHolder.getAdapterPosition()),getApplication());
            }
        }).attachToRecyclerView(recyclerView);

    }

    private void observe() {
        viewModel.getUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersList= users;
                userAdapter.setUsers(users);
                recyclerView.setAdapter(userAdapter);
            }
        });
    }

    private void setUpStuff() {
        viewModel = ViewModelProviders.of(this).get(AdminViewModel.class);
        viewModel.init(getApplication());
        recyclerView = findViewById(R.id.usersRecyclerView);
        addUserButton = findViewById(R.id.addUser);
        addExamButton = findViewById(R.id.addExam);
        userAdapter = new UserAdapter(getApplicationContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void getData(int id,String firstName, String lastName, String email, String password, String course, int age,int num) {
        getSupportFragmentManager()
                .beginTransaction().
                remove(getSupportFragmentManager().findFragmentById(R.id.CreateUserFrame)).commit();
        User user = new User(firstName,lastName,email,password,course,age,1,0);
        if(num==0)viewModel.createUser(user, getApplication());
        else  viewModel.updateUser(id,firstName,lastName,email,password,course,age,getApplication());
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.CreateUserFrame) != null) {
            getSupportFragmentManager()
                    .beginTransaction().
                    remove(getSupportFragmentManager().findFragmentById(R.id.CreateUserFrame)).commit();
        }else finish();
    }

    @Override
    public void onNoteClick(int position) {
        User user = usersList.get(position);
        Fragment fragment = new UpdateUserFragment();
        iFragment updateFragment = (iFragment)fragment;
        updateFragment.setActivity(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.CreateUserFrame,fragment).commit();
        updateFragment.reciveData(user);
    }
}