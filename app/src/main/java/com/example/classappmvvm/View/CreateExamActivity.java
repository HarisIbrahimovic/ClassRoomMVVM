package com.example.classappmvvm.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.classappmvvm.Adapter.QuestionsAdapter;
import com.example.classappmvvm.Fragments.questionFragment;
import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.R;
import com.example.classappmvvm.ViewModel.ExamViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CreateExamActivity extends AppCompatActivity implements QuestionsAdapter.onTouchListener{
    private EditText examNameEditText;
    private EditText examCourseEditText;
    private RecyclerView recyclerView;
    private FloatingActionButton addQuestionButton;
    private ExamViewModel viewModel;
    private QuestionsAdapter questionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        setUpViews();
        onClicks();
        observe();
    }

    private void observe() {
        viewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                questionsAdapter.setQuestionList(questions);
                questionsAdapter.notifyDataSetChanged();
            }
        });

    }

    private void onClicks() {
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValues();
            }
        });
    }

    private void checkValues() {
        String name = examNameEditText.getText().toString().trim();
        String courseName = examCourseEditText.getText().toString().trim();
        if(viewModel.getExamMade()==1){
            addQuestion();
            return;
        }
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(courseName)){
            Toast.makeText(this, "Please fill in the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        Exam exam = new Exam(getIntent().getStringExtra("examId"),name,courseName);
        examCourseEditText.setFocusable(false);
        examNameEditText.setFocusable(false);
        viewModel.addExam(getApplication(),exam);
        viewModel.setExamMade(1);
        addQuestion();
        Toast.makeText(getApplicationContext(),"Exam created",Toast.LENGTH_SHORT).show();
    }

    private void addQuestion() {
        Fragment fragment =new questionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.createQuestionFrame,fragment).commit();
    }

    private void setUpViews() {
        examNameEditText = findViewById(R.id.examName);
        examCourseEditText = findViewById(R.id.examCourseName);
        recyclerView = findViewById(R.id.QuestionsRecView);
        addQuestionButton = findViewById(R.id.addQuestionButton);
        viewModel = ViewModelProviders.of(this).get(ExamViewModel.class);
        viewModel.init(getApplication(),getIntent().getStringExtra("examId"));
        questionsAdapter = new QuestionsAdapter(getApplicationContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(questionsAdapter);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.createQuestionFrame) != null) {
            getSupportFragmentManager()
                    .beginTransaction().
                    remove(getSupportFragmentManager().findFragmentById(R.id.createQuestionFrame)).commit();
        }else finish();
    }

    @Override
    public void onNoteClick(int position) {

    }

    @Override
    public void addPoints(Question qUestion, String answer) {

    }


}