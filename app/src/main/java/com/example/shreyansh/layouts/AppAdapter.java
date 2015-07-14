package com.example.shreyansh.layouts;

import android.app.Activity;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * This Class is for displaying app names with there logos and package name in a listview.
 */

public class AppAdapter extends BaseAdapter {

    private List<ResolveInfo> appsList = null;
    private Activity myActivity;

    public AppAdapter(Activity paramActivity, List<ResolveInfo> paramList){
        appsList = paramList;
        myActivity = paramActivity;
    }

    @Override
    public int getCount() {
        return appsList.size();
    }

    @Override
    public ResolveInfo getItem(int position) {
        return appsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = myActivity.getLayoutInflater();

            //for list view
            convertView = inflater.inflate(R.layout.app_layout, parent, false);

            viewHolder = new MyViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.app_name);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.app_package);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        ResolveInfo info = appsList.get(position);
        if(info != null) {
            Drawable icon = myActivity.getPackageManager().getApplicationIcon(info.activityInfo.applicationInfo);

            viewHolder.image.setImageDrawable(icon);
            viewHolder.textView1.setText(appsList.get(position).loadLabel(myActivity.getPackageManager()));
            viewHolder.textView2.setText(info.activityInfo.applicationInfo.packageName);
        } else {
            Logger.log("got null info");
        }

        return convertView;
    }

    private class MyViewHolder {
        private ImageView image;
        private TextView textView1, textView2;
    }
}
