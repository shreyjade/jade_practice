package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class ListViewActivity extends Activity {

    String[] number = {"one", "two","three","four","five","six","seven","eight","nine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
/*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.textview,number);
        ListView listView = (ListView) findViewById(R.id.number_list);
        listView.setAdapter(adapter);*/

        ListView listView = (ListView) findViewById(R.id.number_list);
        MyCustomAdapter adapter=new MyCustomAdapter(Student.getStudentDataForListView(),this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
