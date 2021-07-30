package com.example.classappmvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.R;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {
    private List<Exam> examList;
    private Context context;
    private TouchListener touchListener;

    public ExamAdapter(Context context, TouchListener touchListener) {
        this.context = context;
        this.touchListener = touchListener;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exam_item,parent,false);
        return new ViewHolder(view,touchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.examName.setText(examList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(examList==null)return 0;
        return examList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView examName = itemView.findViewById(R.id.examNameItem);
        public ViewHolder(@NonNull View itemView, TouchListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            touchListener = listener;
        }

        @Override
        public void onClick(View v) {
            touchListener.onNoteClickes(getAdapterPosition());
        }
    }

    public interface TouchListener{
        void onNoteClickes(int position);
    }
}
