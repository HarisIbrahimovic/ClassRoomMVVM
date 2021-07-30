package com.example.classappmvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.Model.QuestionDao;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;

import java.util.List;

public class StartExamRepository {
    private UserDao userDao;
    private QuestionDao questionDao;
    static StartExamRepository instance;
    private LiveData<List<Question>> questionList;


    public LiveData<List<Question>> getQuestionList(Application application,String id) {
        questionDao = Db.getInstance(application).questionDao();
        questionList=questionDao.getAllQuestions(id);
        return questionList;
    }

    public static StartExamRepository getInstance() {
        if(instance==null)instance=new StartExamRepository();
        return instance;
    }

    public void updateGrades(Application application,int id, int points){
        userDao = Db.getInstance(application).userDao();
        int grade=(points+10)/10;
        if(grade>10)grade=10;
        User user = new User(id,points,grade);
        new UpdateGrades(userDao).execute(user);
    }

    public static class UpdateGrades extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        public UpdateGrades(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updatePoints(users[0].getId(),users[0].getPoints());
            userDao.updateGrade(users[0].getId(),users[0].getGrade());
            return null;
        }
    }
}
