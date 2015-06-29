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

    List<Student> studentList;
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
        MyViewHolder viewHolder;

        //Logger.log("Position : " + position + "   convertView is null : " + (convertView == null));

        if(convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();

            //for list view
            //convertView = inflater.inflate(R.layout.clock_text_include, parent, false);

            //for grid view
            convertView = inflater.inflate(R.layout.clock_text_grid, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.tv1.setText(studentList.get(position).name);
        viewHolder.tv2.setText(studentList.get(position).fatherName);
        viewHolder.tv3.setText(studentList.get(position).motherName);

        if(position%3 == 0) viewHolder.image.setImageResource(R.drawable.clock);
        else if (position%3 == 1) viewHolder.image.setImageResource(R.drawable.virat);
        else viewHolder.image.setImageResource(R.drawable.spiderman);

        return convertView;
    }

    private class MyViewHolder {
        private ImageView image;
        private TextView tv1,tv2,tv3;

        public MyViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.image1);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}