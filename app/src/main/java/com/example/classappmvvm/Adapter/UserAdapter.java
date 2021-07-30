package com.example.classappmvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;
    private Context context;
    private TouchListener touchListener;

    public UserAdapter(Context context, TouchListener touchListener) {
        this.context = context;
        this.touchListener = touchListener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUserAt(int position){
        return users.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view,touchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.userPoints.setText(String.valueOf(user.getPoints())+" ("+String.valueOf(user.getGrade())+")");
        holder.userName.setText(user.getFirstName()+" "+user.getLastName());
        holder.userCourse.setText(user.getCourse());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView userName = itemView.findViewById(R.id.userName);
        TextView userPoints = itemView.findViewById(R.id.userPoints);
        TextView userCourse = itemView.findViewById(R.id.courseName);
        public ViewHolder(@NonNull View itemView,TouchListener tListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            touchListener = tListener;
        }

        @Override
        public void onClick(View v) {
            touchListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface TouchListener{
        void onNoteClick(int position);
    }
}
