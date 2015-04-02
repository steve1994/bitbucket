package com.example.steve.tessuitmediaandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EnterNameActivity extends ActionBarActivity {
    private EditText inputNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        inputNama = (EditText) findViewById(R.id.nameInput);
    }

    public void onEnterNameButtonClick(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("nama", inputNama.getText().toString());
        startActivity(intent);
    }
}
