package com.example.steve.tessuitmediaandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainMenuActivity extends ActionBarActivity {
    private Button pilihEventButton;
    private Button pilihGuestButton;
    private TextView textNama;
    private static final int REQUEST_CODE_EVENT = 1;
    private static final int REQUEST_CODE_GUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        pilihEventButton = (Button) findViewById(R.id.pilihEventButton);
        pilihGuestButton = (Button) findViewById(R.id.pilihGuestButton);
        textNama = (TextView) findViewById(R.id.nameView);

        Bundle bundle = getIntent().getExtras();
        textNama.setText(bundle.getString("nama"));
    }

    public void onPilihEventButtonClick(View view) {
        Intent intent = new Intent(this, EventActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EVENT);
    }

    public void onPilihGuestButtonClick(View view) {
        Intent intent = new Intent(this, GuestActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_EVENT) {                                            // Event activity, request code 1
                if (data.hasExtra("nama event")) {
                    String namaEvent = data.getExtras().getString("nama event");
                    pilihEventButton.setText(namaEvent);
                }
            } else if (requestCode == REQUEST_CODE_GUEST) {                                     // Guest Activity, request code 2
                if (data.hasExtra("nama guest")) {
                    String namaGuest = data.getExtras().getString("nama guest");
                    pilihGuestButton.setText(namaGuest);
                    if (data.hasExtra("birthday guest")) {
                        String birthdayGuest = data.getExtras().getString("birthday guest");
                        String[] parseBirthdayGuest = birthdayGuest.split("[-]");
                        int dayBirthdayGuest = Integer.parseInt(parseBirthdayGuest[2]);
                        if ((dayBirthdayGuest % 2 == 0) && (dayBirthdayGuest % 3 == 0)) {
                            Toast.makeText(getBaseContext(), "iOS", Toast.LENGTH_LONG).show();
                        } else {
                            if (dayBirthdayGuest % 2 == 0) {
                                Toast.makeText(getBaseContext(), "blackberry", Toast.LENGTH_LONG).show();
                            } else if (dayBirthdayGuest % 3 == 0) {
                                Toast.makeText(getBaseContext(), "android", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getBaseContext(), "feature phone", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        }
    }
}
