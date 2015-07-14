package com.example.shreyansh.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SimpleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    public void getContentOnButtonClick(View view) {
        String flag = "app or contact";
        Button clickedButton = (Button)view;
        String buttonText = (String) clickedButton.getText();
        Intent appIntent = new Intent(this, AppListActivity.class);

        if(buttonText.contentEquals("Get Apps")) {
            appIntent.putExtra(flag,1);
        } else if (buttonText.contentEquals("Get Contacts")){
            appIntent.putExtra(flag,2);
        } else {
            //Error in reading button.
            appIntent.putExtra(flag,0);
        }

        startActivity(appIntent);
    }

    public void startServiceOnButtonClick(View view){
        Intent getAppService = new Intent(this, TellAppService.class);
        startService(getAppService);
    }
}