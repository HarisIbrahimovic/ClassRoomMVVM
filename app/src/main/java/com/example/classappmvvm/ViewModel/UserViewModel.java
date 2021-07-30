package com.example.classappmvvm.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository repository;
    private LiveData<List<Exam>> examList;

    public void  init(Application application, String input) {
        repository = UserRepository.getInstance();
        examList = repository.getExamList(application, input);
    }

    public LiveData<List<Exam>> getExamList() {
        return examList;
    }

    public void updateUser(int id, String firstName, String lastName, String email, String password, String course, int age, Application application) {
        repository.updateUser(id,firstName,lastName,email,password,course,age,application);
    }
}
