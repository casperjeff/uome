package com.greymatters.uome;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//
        return true;
    }
}
