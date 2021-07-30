package com.example.classappmvvm.ViewModel;


import java.util.List;
import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.Repository.StartExamRepository;

public class StartExamViewModel extends ViewModel {
    private StartExamRepository repository;
    private LiveData<List<Question>> questionList;
    private MutableLiveData<Integer> points = new MutableLiveData<>();

    public LiveData<Integer> getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points.postValue(points);
        this.points.setValue(points);
    }
    public void updateGrade(Application application,int id,int points){
        repository.updateGrades(application,id,points);
    }
    public void init(Application application, String id){
       repository = StartExamRepository.getInstance();
       questionList = repository.getQuestionList(application,id);
       setPoints(0);
    }

    public LiveData<List<Question>> getQuestionList() {
        return questionList;
    }
}
