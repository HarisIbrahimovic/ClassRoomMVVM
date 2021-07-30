package com.example.classappmvvm.Repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;

import java.util.List;

public class MainRepository {
    static MainRepository instance;
    private UserDao userDao;
    private LiveData<List<User>> userList;

    public static MainRepository getInstance(){
        if(instance==null)instance= new MainRepository();
        return instance;
    }


    public LiveData<List<User>> getUserList(Application application) {
        setUserList(application);
        return userList;
    }

    private void setUserList(Application application) {
        userDao = Db.getInstance(application).userDao();
        userList = userDao.getAllUsers();
    }

}
