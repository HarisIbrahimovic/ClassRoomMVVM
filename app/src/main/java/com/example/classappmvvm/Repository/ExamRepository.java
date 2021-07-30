package com.example.classappmvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.ExamDao;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.Model.QuestionDao;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;

import java.util.List;

public class ExamRepository {
    static ExamRepository instance;
    public static ExamRepository getInstance(){
        if(instance==null)instance = new ExamRepository();
        return instance;
    }
    private ExamDao examDao;
    private QuestionDao questionDao;
    private LiveData<List<Question>> questions;

    public LiveData<List<Question>> getQuestions(Application application,String examId) {
        questionDao = Db.getInstance(application).questionDao();
        questions = questionDao.getAllQuestions(examId);
        return questions;
    }

    public void addExam(Application application, Exam exam){
        examDao = Db.getInstance(application).examDao();
        new InsertExam(examDao).execute(exam);
    }

    public void addQuestion(Application application, Question question) {
        questionDao = Db.getInstance(application).questionDao();
        new InsertQuestion(questionDao).execute(question);
    }
    private static class InsertQuestion extends AsyncTask<Question, Void, Void>{
        private QuestionDao questionDao;
        private InsertQuestion(QuestionDao questionDao){
            this.questionDao=questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insertQuestion(questions[0]);
            return null;
        }
    }


    private static class InsertExam extends AsyncTask<Exam, Void, Void> {
        private ExamDao examDao;
        private InsertExam(ExamDao examDao) {
            this.examDao = examDao;
        }
        @Override
        protected Void doInBackground(Exam... exams) {
            examDao.insertExam(exams[0]);
            return null;
        }
    }
}
