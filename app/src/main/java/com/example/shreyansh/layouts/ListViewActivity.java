package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends Activity {

    //String[] number = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private MyCustomAdapter mAdapter;
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

        ListView listView = (ListView) findViewById(R.id.number_list);
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

            mAdapter = new MyCustomAdapter(getCurrentActivity(), mStudentsList);
            listView.setAdapter(mAdapter);
        }
    }

    /*
    It deletes the record from list and refreshes the view. It will not delete the image because it
    is not part of the list. To delete the image, remove image setting code from MyCustomAdapter and
    add it to Student class.
    */
    public void deleteRecord(int position){
        mStudentsList.remove(position);
        if(mAdapter != null){
            mAdapter.setmStudentList(mStudentsList);
            mAdapter.notifyDataSetChanged();
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
