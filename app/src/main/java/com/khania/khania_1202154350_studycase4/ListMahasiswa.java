package com.khania.khania_1202154350_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {
    private ListView listNama;
    private Button btnMulai;

    private String[] list_nama = {
            "Khania","Dwima","Fikri","Editha","Amri","Taufan","Aulia"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        listNama = findViewById(R.id.list_nama);
        btnMulai = findViewById(R.id.mulai);

        listNama.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new ArrayList<String>()));

    }

    public void mulai(View view) {
        mulaiAsynctask mulai = new mulaiAsynctask();
        mulai.execute();
    }

    class mulaiAsynctask extends AsyncTask<Void, String, String> {
        ArrayAdapter<String> adapter;
        int count;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>)listNama.getAdapter();
            pDialog = new ProgressDialog(ListMahasiswa.this);
            pDialog.setTitle("Mengambil Data");
            pDialog.setProgressStyle(pDialog.STYLE_HORIZONTAL);
            pDialog.setMax(10);
            pDialog.setProgress(0);
            pDialog.show();
            count = 0;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ListMahasiswa.this,s,Toast.LENGTH_LONG).show();
            pDialog.hide();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            pDialog.setProgress(count);
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String listmhsw : list_nama) {
                publishProgress(listmhsw);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Data Sudah Diambil Semua";
        }
    }
}
