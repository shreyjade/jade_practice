package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends Activity {

    //String[] number = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private MyCustomAdapter mAdapter;
    private ListView mListView;
    private ArrayList<Student> mStudentsList;

    Activity getCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        /*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.textview,number);
        ListView listView = (ListView) findViewById(R.id.number_list);
        listView.setAdapter(adapter);
        */

        mListView = (ListView) findViewById(R.id.number_list);
        mStudentsList = Student.getStudentDataForListView();
        if (mStudentsList != null && mStudentsList.size() > 0) {

            /*
            When user click on the item, it displays its index number in the list.
            Remove if index not required.
            */

/*            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "Deleted record at " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Row selected " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
            });*/

            /*
            It deletes the record from list and refresh the view.
            */
            OnClickListener deleteRecord = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mListView.getPositionForView(v);
                    if (position != ListView.INVALID_POSITION) {
                        Toast.makeText(getApplicationContext(), "Record removed !!", Toast.LENGTH_SHORT).show();
                        mStudentsList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            };
            mAdapter = new MyCustomAdapter(getCurrentActivity(), mStudentsList, deleteRecord);
            mListView.setAdapter(mAdapter);
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
