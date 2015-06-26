package com.example.shreyansh.layouts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends BaseAdapter {

    List<Student> studentList;/* = Student.getStudentDataForListView();*/
    Activity activity;

    public MyCustomAdapter(ArrayList<Student> arrayList, Activity activity) {
        studentList = arrayList;
        this.activity = activity;
    }

    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.clock_text_include, parent, false);

        ((TextView) convertView.findViewById(R.id.tv1)).setText(studentList.get(position).name);
        ((TextView) convertView.findViewById(R.id.tv2)).setText(studentList.get(position).fatherName);
        ((TextView) convertView.findViewById(R.id.tv3)).setText(studentList.get(position).motherName);

        ImageView iv=(ImageView) convertView.findViewById(R.id.image1);
        if(position%3 == 0) iv.setImageResource(R.drawable.clock);
        else if (position%3 == 1) iv.setImageResource(R.drawable.virat);
        else iv.setImageResource(R.drawable.spiderman);

        return convertView;
    }
}
