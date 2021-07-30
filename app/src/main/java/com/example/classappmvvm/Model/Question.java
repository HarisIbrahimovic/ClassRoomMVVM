package com.example.classappmvvm.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_question")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String examId;
    private String question;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String correctAnswer;

    public Question(int id, String examId, String question, String a1, String a2, String a3, String a4, String correctAnswer) {

        this.examId = examId;
        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", examId='" + examId + '\'' +
                ", question='" + question + '\'' +
                ", a1='" + a1 + '\'' +
                ", a2='" + a2 + '\'' +
                ", a3='" + a3 + '\'' +
                ", a4='" + a4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}

