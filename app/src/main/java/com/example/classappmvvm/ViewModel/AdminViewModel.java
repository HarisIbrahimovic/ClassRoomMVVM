package com.example.classappmvvm.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Repository.AdminMenuRepository;
import com.example.classappmvvm.Repository.MainRepository;

import java.util.List;

public class AdminViewModel extends ViewModel {

    private LiveData<List<User>> userList;
    private AdminMenuRepository repository;

    public void init(Application application){
        repository= AdminMenuRepository.getInstance();
        userList = repository.getUserList(application);
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public void createUser(User user, Application application) {
        repository.createUser(user,application);
    }


    public void deleteUser(User user, Application application) {
        repository.deleteUser(user,application);
    }

    public void updateUser(int id, String firstName, String lastName, String email, String password, String course, int age, Application application) {
        repository.updateUser(id,firstName,lastName,email,password,course,age,application);
    }
}
