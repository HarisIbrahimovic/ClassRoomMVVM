package com.example.classappmvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.ExamDao;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;
import java.util.List;

public class UserRepository {
    static UserRepository instance;
    private ExamDao examDao;
    private UserDao userDao;
    public static UserRepository getInstance(){
        if(instance==null)instance= new UserRepository();
        return instance;
    }

    private LiveData<List<Exam>> examList;


    public LiveData<List<Exam>> getExamList(Application application,String input) {
        examDao = Db.getInstance(application).examDao();
        examList= examDao.getMyExams(input);
        return examList;
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

}
