package com.khania.khania_1202154350_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {
    private EditText linkURL;
    private Button btnCari;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        linkURL = findViewById(R.id.url_gambar);
        btnCari = findViewById(R.id.cari_gambar);
        gambar = findViewById(R.id.gambar);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new cariGambar().execute();
            }
        });
    }

    class cariGambar extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {
            String URL = linkURL.getText().toString();
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream((InputStream)new URL(URL).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bmp;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            gambar.setImageBitmap(bitmap);
        }
    }
}
