package com.example.classappmvvm.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.Repository.ExamRepository;

import java.util.List;

public class ExamViewModel extends ViewModel {
    private ExamRepository repository;
    private int examMade;
    private LiveData<List<Question>> questions;

    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

    public int getExamMade() {
        return examMade;
    }

    public void setExamMade(int examMade) {
        this.examMade = examMade;
    }

    public void init(Application application, String examId){
        repository = ExamRepository.getInstance();
        questions= repository.getQuestions(application,examId);
    }

    public void addExam(Application application, Exam exam){
        repository.addExam(application,exam);
    }

    public void addQuestion(Application application, Question question) {
        repository.addQuestion(application,question);
    }
}
