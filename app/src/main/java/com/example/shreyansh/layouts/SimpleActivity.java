package com.example.shreyansh.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modular_clock_text_include);
        Logger.log("created !!");

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
    }

    public void onClickButton(View v) {
        Toast.makeText(getApplication(), "hello !!", Toast.LENGTH_SHORT).show();
    }

    /*public void changeText(View v) {
        Logger.log("inside changeText");
        TextView tv = (TextView) findViewById(v.getId());
        String text = tv.getText().toString();
        Logger.log(text);
    }*/

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
}