package com.juaracoding.ujianandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juaracoding.ujianandroid.soal1.InputPelanggan;
import com.juaracoding.ujianandroid.soal2.SimpleGameGambar;

public class Start extends AppCompatActivity implements View.OnClickListener {

    Button btnSoal1, btnSoal2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnSoal1 = findViewById(R.id.btnSoal1);
        btnSoal2 = findViewById(R.id.btnSoal2);

        btnSoal1.setOnClickListener(this);
        btnSoal2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSoal1:
                Intent input = new Intent (Start.this, SimpleGameGambar.class);
                startActivity(input);
                break;
            case R.id.btnSoal2:
                Intent gambar = new Intent (Start.this, InputPelanggan.class);
                startActivity(gambar);
                break;
        }
    }
}
