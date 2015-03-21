package com.example.steve.tessuitmediaandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class EventActivity extends Activity {
    private ListView listEvent;
    private ArrayList<dataStructureEvent> namaTanggalEvent;
    private int positionSelected = 0;
    private rowEventList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        listEvent = (ListView) findViewById(R.id.listEvent);

        // Pasang list di atas dengan kelas AdapterArray rowEventList
        namaTanggalEvent = new ArrayList<dataStructureEvent>();
        adapter = new rowEventList(this, namaTanggalEvent);
        listEvent.setAdapter(adapter);

        // Pasang event click listener pada list event untuk handle data
        listEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                finish();
            }
        });

        // Tambahkan list event pada adapter yang sudah dibind dengan list view
        adapter.add(new dataStructureEvent("acara ulang tahun", "20 Maret 2015"));
        adapter.add(new dataStructureEvent("acara olahraga", "21 Maret 2015"));
        adapter.add(new dataStructureEvent("acara reuni sekolah", "22 Maret 2015"));
        adapter.add(new dataStructureEvent("acara rapat", "23 Maret 2015"));
        adapter.add(new dataStructureEvent("acara rekreasi", "24 Maret 2015"));
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("nama event", namaTanggalEvent.get(positionSelected).getNama());
        setResult(RESULT_OK, intent);
        adapter.clear();
        super.finish();
    }
}
