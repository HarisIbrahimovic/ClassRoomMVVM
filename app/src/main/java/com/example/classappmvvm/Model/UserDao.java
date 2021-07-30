package com.example.classappmvvm.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User user);

    @Query("UPDATE table_users SET firstName = :first_name WHERE id = :inputId ")
    void updateFirstName(int inputId,String first_name);

    @Query("UPDATE table_users SET lastName = :last_name WHERE id = :inputId ")
    void updateLastName(int inputId,String last_name);

    @Query("UPDATE table_users SET password = :Password WHERE id = :inputId ")
    void updatePassword(int inputId,String Password);

    @Query("UPDATE table_users SET email = :Email WHERE id = :inputId ")
    void updateEmail(int inputId,String Email);

    @Query("UPDATE table_users SET age = :Age WHERE id = :inputId ")
    void updateAge(int inputId,int Age);

    @Query("UPDATE table_users SET points = :iPoints WHERE id = :inputId ")
    void updatePoints(int inputId,int iPoints);

    @Query("UPDATE table_users SET grade = :iGrade WHERE id = :inputId ")
    void updateGrade(int inputId,int iGrade);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM table_users WHERE firstName LIKE :inputName ")
    LiveData<List<User>> getSearchUsers(String inputName);

    @Query("SELECT * FROM table_users ")
    LiveData<List<User>> getAllUsers();

}
