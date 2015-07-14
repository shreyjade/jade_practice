package com.example.shreyansh.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class AppListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        AppAdapter mAdapter;
        ListView listView = (ListView) findViewById(R.id.content_list);
        int flag = getIntent().getIntExtra("app or contact",0);

        //Get app data in listview
        if (flag==1) {
            Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0);
            if (pkgAppsList != null) {
                mAdapter = new AppAdapter(this, pkgAppsList);
                listView.setAdapter(mAdapter);
            }
        }

        //Get contact data in listview
        else if (flag == 2){
            //Write code to get contacts and set it in list. Then set adapter.
        }

        //Error in reading text on button
        else {
            Logger.log("Error in reading text on button !!");
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

