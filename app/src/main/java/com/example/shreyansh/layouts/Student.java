package com.example.shreyansh.layouts;

import java.util.ArrayList;

public class Student {
    String name;
    String fatherName;
    String motherName;

    public static ArrayList<Student> getStudentDataForListView()
    {
        ArrayList<Student> StudentList = new ArrayList<Student>();
        for(int i=0;i<20;i++)
        {
            Student student = new Student();
            student.name = "Name "+i;
            student.fatherName = "Father "+i;
            student.motherName = "Mother "+i;
            StudentList.add(student);
        }
        return StudentList;
    }
}