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
        String name = bundle.getString("nama");
        textNama.setText(name);

        // Cek nama apakah palindrom atau bukan
        if (isNamaPalindrom(name)) {
            Toast.makeText(getBaseContext(),"Nama Anda berbentuk palindrom",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(),"Nama Anda bukan berbentuk palindrom",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNamaPalindrom (String nama) {
        int indexDepan = 0;
        int indexBelakang = nama.length() - 1;
        boolean palindrom = true;

        do {
            if (nama.charAt(indexDepan) != nama.charAt(indexBelakang)) {
                palindrom = false;
            }
            indexDepan++;
            indexBelakang--;
        } while (indexDepan <= indexBelakang);

        return palindrom;
    }

    private boolean isMonthPrime(int month) {
        boolean Prime;
        switch (month) {
            case 2 :
            case 3 :
            case 5 :
            case 7 :
            case 11 :
                Prime = true; break;
            default :
                Prime = false; break;
        }
        return Prime;
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
                        int dayBirthdayGuest = Integer.parseInt(parseBirthdayGuest[2]);         // Dapat hari dari birthday guest
                        int monthBirthdayGuest = Integer.parseInt(parseBirthdayGuest[1]);       // Dapat bulan dari birthday guest

                        String kategoriDay;
                        if ((dayBirthdayGuest % 2 == 0) && (dayBirthdayGuest % 3 == 0)) {
                            kategoriDay = "iOS";
                        } else {
                            if (dayBirthdayGuest % 2 == 0) {
                                kategoriDay = "blackberry";
                            } else if (dayBirthdayGuest % 3 == 0) {
                                kategoriDay = "android";
                            } else {
                                kategoriDay = "feature phone";
                            }
                        }

                        String kategoriMonth;
                        if (isMonthPrime(monthBirthdayGuest)) {
                            kategoriMonth = "prima";
                        } else {
                            kategoriMonth = "bukan prima";
                        }

                        String toastMessage = kategoriDay + " dan " + kategoriMonth;
                        Toast.makeText(getBaseContext(), toastMessage, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
