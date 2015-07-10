package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        ArrayList<Student> studentsList = Student.getStudentDataForListView();

        /*
        below 2 lines are commented because MyCustomAdapter has been changed and this class hasn't been revised according to it.
        send OnClickListner object in MyCustomAdapter constructor to make it work.
        */
//        MyCustomAdapter adapter = new MyCustomAdapter(this, studentsList);
//       gridView.setAdapter(adapter);

/*        When user click on the item, it displays its index number in the list.
        Remove if index not required.
 */

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer index = position;
                Toast.makeText(getApplicationContext(), index.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
