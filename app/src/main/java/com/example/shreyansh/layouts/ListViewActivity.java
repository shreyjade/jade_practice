package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListViewActivity extends Activity {

    //String[] number = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
/*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.textview,number);
        ListView listView = (ListView) findViewById(R.id.number_list);
        listView.setAdapter(adapter);*/

        ListView listView = (ListView) findViewById(R.id.number_list);
        ArrayList<Student> studentsList = Student.getStudentDataForListView();
        if(studentsList != null && studentsList.size()>0){
            MyCustomAdapter adapter = new MyCustomAdapter(studentsList, this);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Integer index = position;
                    Toast.makeText(getApplicationContext(), index.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

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
