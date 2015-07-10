package com.example.shreyansh.layouts;

import java.util.ArrayList;

public class Student {
    private String mName;
    private String mFatherName;
    private String mMotherName;

    public static ArrayList<Student> getStudentDataForListView() {
        ArrayList<Student> StudentList = new ArrayList<Student>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setName("Name " + i);
            student.setFatherName("Father " + i);
            student.setMotherName("Mother " + i);
            StudentList.add(student);
        }
        return StudentList;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getFatherName() {
        return mFatherName;
    }

    public void setFatherName(String mFatherName) {
        this.mFatherName = mFatherName;
    }

    public String getMotherName() {
        return mMotherName;
    }

    public void setMotherName(String mMotherName) {
        this.mMotherName = mMotherName;
    }
}