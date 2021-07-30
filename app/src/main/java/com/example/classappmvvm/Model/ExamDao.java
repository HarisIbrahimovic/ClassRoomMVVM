package com.example.classappmvvm.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExam(Exam exam);

    @Query("DELETE FROM table_exam WHERE id = :inputId ")
    void delete(int inputId);

    @Query("UPDATE table_exam SET name = :inputName WHERE id = :inputId ")
    void updateExam(int inputId, String inputName);

    @Query("SELECT * FROM table_exam WHERE course = :input")
    LiveData<List<Exam>> getMyExams(String input);

}
