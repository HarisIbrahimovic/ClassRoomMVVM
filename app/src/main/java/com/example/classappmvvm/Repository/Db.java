package com.example.classappmvvm.Repository;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.classappmvvm.Model.Exam;
import com.example.classappmvvm.Model.ExamDao;
import com.example.classappmvvm.Model.Question;
import com.example.classappmvvm.Model.QuestionDao;
import com.example.classappmvvm.Model.User;
import com.example.classappmvvm.Model.UserDao;

@Database(entities = {Exam.class, Question.class, User.class}, version = 1,exportSchema = false)
abstract class Db extends RoomDatabase {
    public abstract ExamDao examDao();
    public abstract QuestionDao questionDao();
    public abstract UserDao userDao();
    public static Db instance;
    public static synchronized Db getInstance(Application application){
        if(instance==null){
            instance = Room.databaseBuilder(application.getApplicationContext(), Db.class, "my_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
        }
    };

}
