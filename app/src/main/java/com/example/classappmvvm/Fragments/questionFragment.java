package com.example.classappmvvm.Fragments;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.R;
import com.example.classappmvvm.ViewModel.ExamViewModel;


public class questionFragment extends Fragment {

    private EditText QuestionEditText;
    private EditText AnswerOneEditText;
    private EditText AnswerTwoEditText;
    private EditText AnswerThreeEditText;
    private EditText AnswerFourEditText;
    private EditText CorrectAnswer;
    private Button addQuestionButton;
    private ExamViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        setUpViews(view);
        onClicks();
        return view;
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
        String sQuestion = QuestionEditText.getText().toString();
        String one = AnswerOneEditText.getText().toString();
        String two = AnswerTwoEditText.getText().toString();
        String three = AnswerThreeEditText.getText().toString();
        String four = AnswerFourEditText.getText().toString();
        String correctAnswer = CorrectAnswer.getText().toString();
        if(TextUtils.isEmpty(one)||TextUtils.isEmpty(two)||TextUtils.isEmpty(three)||TextUtils.isEmpty(four)||TextUtils.isEmpty(sQuestion)||TextUtils.isEmpty(correctAnswer)){
            Toast.makeText(getActivity().getApplicationContext(),"Fill in the fields",Toast.LENGTH_SHORT).show();
            return;
        }
        String examId = getActivity().getIntent().getStringExtra("examId");
        Question question = new Question(-1,examId,sQuestion,one,two,three,four,correctAnswer);
        viewModel.addQuestion(getActivity().getApplication(),question);
        getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .remove(this)
                    .commit();
    }

    private void setUpViews(View view) {
        viewModel = ViewModelProviders.of(this).get(ExamViewModel.class);
        viewModel.init(getActivity().getApplication(),getActivity().getIntent().getStringExtra("examId"));
        QuestionEditText = view.findViewById(R.id.questionCreate);
        AnswerOneEditText = view.findViewById(R.id.answerA);
        AnswerTwoEditText = view.findViewById(R.id.answerB);
        AnswerThreeEditText = view.findViewById(R.id.answerC);
        AnswerFourEditText = view.findViewById(R.id.answerD);
        CorrectAnswer = view.findViewById(R.id.correctAnswer);
        addQuestionButton = view.findViewById(R.id.addThisQuestionButton);
    }
}