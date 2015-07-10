package com.example.shreyansh.layouts;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends Activity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        fragmentManager = getFragmentManager();
    }

    public void onClickButton(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Button clickedButton = (Button) view;
        String buttonText = (String) clickedButton.getText();
        if (buttonText.contentEquals("READ TEXT")) {
            FragmentOne fragmentOne = new FragmentOne();
            fragmentTransaction.replace(R.id.my_fragment, fragmentOne, "one");
        } else {
            FragmentTwo fragmentTwo = new FragmentTwo();
            fragmentTransaction.replace(R.id.my_fragment, fragmentTwo, "two");
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
