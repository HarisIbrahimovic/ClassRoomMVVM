package com.example.classappmvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<Question> questionList;
    private Context context;
    private onTouchListener touchListener;
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }


    public QuestionsAdapter(Context context, onTouchListener touchListener) {
        this.context = context;
        this.touchListener = touchListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item,parent,false);
        return new ViewHolder(view,touchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.questionText.setText(question.getQuestion());
        holder.button1.setText(question.getA1());
        holder.button2.setText(question.getA2());
        holder.button3.setText(question.getA3());
        holder.button4.setText(question.getA4());
        setOnClicks(holder,question);
    }

    private void setOnClicks(ViewHolder holder, Question question) {
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchListener.addPoints(question,holder.button1.getText().toString());
                setFocusable(holder);
            }
        });
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchListener.addPoints(question,holder.button2.getText().toString());
                setFocusable(holder);
            }
        });
        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchListener.addPoints(question,holder.button3.getText().toString());
                setFocusable(holder);
            }
        }); holder.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchListener.addPoints(question,holder.button4.getText().toString());
                setFocusable(holder);
            }
        });
    }

    private void setFocusable(ViewHolder holder) {
        holder.button1.setClickable(false);
        holder.button2.setClickable(false);
        holder.button3.setClickable(false);
        holder.button4.setClickable(false);
    }

    @Override
    public int getItemCount() {
        if(questionList==null)return 0;
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView questionText = itemView.findViewById(R.id.questionText);
        private RadioButton button1 = itemView.findViewById(R.id.one);
        private RadioButton button2 = itemView.findViewById(R.id.two);
        private RadioButton button3 = itemView.findViewById(R.id.three);
        private RadioButton button4 = itemView.findViewById(R.id.four);
        public ViewHolder(@NonNull View itemView, onTouchListener tListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            touchListener = tListener;
        }

        @Override
        public void onClick(View v) {
            touchListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onTouchListener{
        void onNoteClick(int position);
        void addPoints(Question qUestion,String answer);
    }
}
