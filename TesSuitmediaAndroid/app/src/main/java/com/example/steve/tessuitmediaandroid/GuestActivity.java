package com.example.steve.tessuitmediaandroid;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class GuestActivity extends Activity {
    private GridView listGuest;
    private ArrayList<dataStructureGuest> namaBirthdateGuest;
    private List<dataStructureGuest> tempRequestResult;
    private int positionSelected;
    private rowGuestList adapter;

    private class requestListGuest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... endpoint_url) {
            // String response doInBackground
            String response = null;

            // Buat objek http dan url
            URL endpoint = null;
            try {
                endpoint = new URL(endpoint_url[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection askEndpoint = null;
            try {
                assert endpoint != null;
                askEndpoint = (HttpURLConnection) endpoint.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Baca response dari request endpoint dalam bentuk buffer stream
            BufferedReader reader = null;
            try {
                assert askEndpoint != null;
                reader = new BufferedReader(new InputStreamReader(askEndpoint.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Ubah stream buffer dari response ke string
            try {
                assert reader != null;
                response = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tutup koneksi endpoint
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;

            /*// Create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = null;
            try {
                httpResponse = httpclient.execute(new HttpGet(endpoint_url[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Receive response as inputStream
            InputStream inputStream;
            StringBuilder response = null;
            try {
                assert httpResponse != null;
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    // Convert InputStream to string response http
                    BufferedReader bufferedReader = null;
                    if (inputStream != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    }
                    String line;
                    response = new StringBuilder();
                    try {
                        assert bufferedReader != null;
                        while ((line = bufferedReader.readLine()) != null) {
                            response.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Tutup koneksi stream input
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.w("Entity", "Entity kosong");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            if (response != null) {
                Log.d("response", response.toString());
                System.out.println(response.toString());
            }

            assert response != null;
            return response.toString();*/
        }

        private void getListGuestJSON (String response) {
            // Parse string response dalam format json, masukkan ke list vector listGuest

            JSONArray guest = null;
            try {
                guest = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            assert guest != null;
            for (int i=0; i<guest.length(); i++) {
                JSONObject itemGuest = null;
                try {
                    itemGuest = guest.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String nama = null;
                try {
                    assert itemGuest != null;
                    nama = itemGuest.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String birthday = null;
                try {
                    birthday = itemGuest.getString("birthdate");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                namaBirthdateGuest.add(i, new dataStructureGuest(nama, birthday));
                //tempRequestResult.add(i, new dataStructureGuest(nama, birthday));
                //adapter.add(new dataStructureGuest(nama, birthday));
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
            getListGuestJSON(result);
           /* // Tambahkan data guest hasil dari request ke adapter
            Log.d("result", tempRequestResult.get(1).getNama());
            Toast.makeText(getBaseContext(), tempRequestResult.get(1).getNama(), Toast.LENGTH_LONG).show();
            for (int i=0; i<tempRequestResult.size(); i++) {
                String nama = tempRequestResult.get(i).getNama();
                String birthdate = tempRequestResult.get(i).getBirthday();
                adapter.add(new dataStructureGuest(nama,birthdate));
            }*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        listGuest = (GridView) findViewById(R.id.listGuest);

        // Request data guest dari endpoint kemudian isi list guest
        requestListGuest requestList = new requestListGuest();
        requestList.execute("http://dry-sierra-6832.herokuapp.com/api/people");

        // Bind list nameBirthdayGuest ke listview list of guest beserta setting click listener
        namaBirthdateGuest = new ArrayList<dataStructureGuest>();
        adapter = new rowGuestList(this, namaBirthdateGuest);
        listGuest.setAdapter(adapter);

        listGuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
                finish();
            }
        });

        Toast.makeText(getBaseContext(), "Request guest dari url berhasil", Toast.LENGTH_LONG).show();

       /* // Tambahkan data guest hasil dari request ke adapter
        Log.d("result", tempRequestResult.get(1).getNama());
        Toast.makeText(getBaseContext(), tempRequestResult.get(1).getNama(), Toast.LENGTH_LONG).show();
        for (int i=0; i<tempRequestResult.size(); i++) {
            String nama = tempRequestResult.get(i).getNama();
            String birthdate = tempRequestResult.get(i).getBirthday();
            adapter.add(new dataStructureGuest(nama,birthdate));
        }*/
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("nama guest", namaBirthdateGuest.get(positionSelected).getNama());
        intent.putExtra("birthday guest", namaBirthdateGuest.get(positionSelected).getBirthday());
        setResult(RESULT_OK, intent);
       // adapter.clear();
        super.finish();
    }
}
