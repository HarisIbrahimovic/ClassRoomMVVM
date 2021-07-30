package com.example.classappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classappmvvm.Adapter.QuestionsAdapter;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.R;
import com.example.classappmvvm.ViewModel.StartExamViewModel;

import java.util.List;

public class StartExamActivity extends AppCompatActivity implements QuestionsAdapter.onTouchListener {

    private TextView examName ;
    private TextView points;
    private RecyclerView recyclerView;
    private QuestionsAdapter questionsAdapter;
    private StartExamViewModel viewModel;
    private Button finish;
    private List<Question> questionList;
    private int userPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);
        setUpViews();
        setUpRecView();
        observe();
        onClicks();

    }

    private void observe() {
        viewModel.getQuestionList().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                questionsAdapter.setQuestionList(questions);
                questionsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(questionsAdapter);
                questionList = questions;
            }
        });
        viewModel.getPoints().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                userPoints = integer;
                points.setText("Points: "+userPoints);
            }
        });
    }

    private void onClicks() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.updateGrade(getApplication(),getIntent().getIntExtra("id",0),userPoints);
                Toast.makeText(StartExamActivity.this, "Exam finished.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setUpRecView() {
        recyclerView = findViewById(R.id.startExamQuestions);
        questionsAdapter = new QuestionsAdapter(getApplicationContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void setUpViews() {
        viewModel = ViewModelProviders.of(this).get(StartExamViewModel.class);
        viewModel.init(getApplication(),getIntent().getStringExtra("examId"));
        examName = findViewById(R.id.startExamName);
        points = findViewById(R.id.startExamPoints);
        finish = findViewById(R.id.ButtonFinishTest);
    }

    @Override
    public void onNoteClick(int position) {

    }

    @Override
    public void addPoints(Question qUestion, String answer) {
        if(qUestion.getCorrectAnswer().equals(answer)){
            Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
            viewModel.setPoints(viewModel.getPoints().getValue()+100/(questionList.size()));
        }else Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
    }
}