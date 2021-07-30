package com.example.classappmvvm.ViewModel;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;
import com.example.classappmvvm.Repository.MainRepository;

import java.util.HashMap;
import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> loginSuccesState = new MutableLiveData<>();
    private MutableLiveData<User> user = new MutableLiveData<>();
    private LiveData<List<User>> userList;
    private MainRepository repository;


    public void init(Application application){
        loginSuccesState.setValue(-1);
        repository= MainRepository.getInstance();
        userList = repository.getUserList(application);
    }

    public void setUser(User user) {
        this.user.postValue(user);
        this.user.setValue(user);
    }

    public void setLoginSuccesState(int value) {
        loginSuccesState.postValue(value);
        loginSuccesState.setValue(value);
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<Integer> getLoginSuccesState() {
        return loginSuccesState;
    }



    public void loginUser(String email, String password,List<User> userList) {
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
            setLoginSuccesState(0);
            return;
        }
        if(email.equals("admin")&&password.equals("admin")){
            setLoginSuccesState(1);
            return;
        }

        if(userList==null)return;
        for(User user : userList){
            if(user.getEmail().equals(email)&&user.getPassword().equals(password)){
                setUser(user);
                return;
            }
        }
        setLoginSuccesState(3);
    }
}
