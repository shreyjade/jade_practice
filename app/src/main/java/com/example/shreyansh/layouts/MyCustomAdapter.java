package com.example.shreyansh.layouts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends BaseAdapter {

    private List<Student> mStudentList;
    private Activity mActivity;
    private View.OnClickListener mDeleteRecordListner;

    public MyCustomAdapter(Activity paramActivity, ArrayList<Student> paramStudentList, View.OnClickListener paramListner) {
        mStudentList = paramStudentList;
        mActivity = paramActivity;
        mDeleteRecordListner = paramListner;
    }

    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;

        //Logger.log("Position : " + position + "   convertView is null : " + (convertView == null));

        if (convertView == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();

            //for list view
            convertView = inflater.inflate(R.layout.clock_text_include, parent, false);

            //for grid view
            //convertView = inflater.inflate(R.layout.clock_text_grid, parent, false);

            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.textView1.setText(mStudentList.get(position).getName());
        viewHolder.textView2.setText(mStudentList.get(position).getFatherName());
        viewHolder.textView3.setText(mStudentList.get(position).getMotherName());

        ((Button) convertView.findViewById(R.id.cancel_button)).setOnClickListener(mDeleteRecordListner);

        if (position % 3 == 0) viewHolder.image.setImageResource(R.drawable.clock);
        else if (position % 3 == 1) viewHolder.image.setImageResource(R.drawable.virat);
        else viewHolder.image.setImageResource(R.drawable.spiderman);

        return convertView;
    }

    private class MyViewHolder {
        private ImageView image;
        private TextView textView1, textView2, textView3;

        public MyViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image1);
            textView1 = (TextView) view.findViewById(R.id.tv1);
            textView2 = (TextView) view.findViewById(R.id.tv2);
            textView3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
