package com.misis.RedisLab6.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private String fullName; // ФИО студента
    private LocalDate birthday; // Дата рождения
    private int randomValue; // Случайное значение

    public Student() {}

    public Student(String fullName, LocalDate birthday, int randomValue) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.randomValue = randomValue;
    }

    // Геттеры и сеттеры
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getRandomValue() {
        return randomValue;
    }

    public void setRandomValue(int randomValue) {
        this.randomValue = randomValue;
    }
}