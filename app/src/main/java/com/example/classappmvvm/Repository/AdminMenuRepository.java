package com.example.classappmvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;

import java.util.List;

public class AdminMenuRepository {
    static AdminMenuRepository instance;


    public static AdminMenuRepository getInstance(){
        if(instance==null)instance= new AdminMenuRepository();
        return instance;
    }

    private UserDao userDao;
    private LiveData<List<User>> userList;
    public LiveData<List<User>> getUserList(Application application) {
        setUserList(application);
        return userList;
    }

    private void setUserList(Application application) {
        userDao = Db.getInstance(application).userDao();
        userList = userDao.getAllUsers();
    }

    public void createUser(User user, Application application) {
        userDao = Db.getInstance(application).userDao();
        new InsertUser(userDao).execute(user);
    }

    public void deleteUser(User user, Application application) {
        userDao = Db.getInstance(application).userDao();
        new DeleteUser(userDao).execute(user);
    }

    public void updateUser(int id, String firstName, String lastName, String email, String password, String course, int age, Application application) {
        userDao = Db.getInstance(application).userDao();
        User user = new User(id,firstName,lastName,email,password,course,age,0,0);
        new UpdateUser(userDao).execute(user);

    }

    private static class UpdateUser extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private UpdateUser(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.updateAge(users[0].getId(),users[0].getAge());
            userDao.updateFirstName(users[0].getId(),users[0].getFirstName());
            userDao.updateLastName(users[0].getId(),users[0].getLastName());
            userDao.updateEmail(users[0].getId(),users[0].getEmail());
            userDao.updatePassword(users[0].getId(),users[0].getPassword());
            return null;
        }
    }


    private static class InsertUser extends AsyncTask<User, Void, Void> {
        private UserDao userDao;
        private InsertUser(UserDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users[0]);
            return null;
        }
    }

    private static class DeleteUser extends AsyncTask<User, Void, Void>{
        private UserDao userDao;
        private DeleteUser(UserDao userDao){
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }
}
