package com.luisfelipegomezc.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {


    EditText pExpo, pPrac, pProy;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        pExpo = (EditText) findViewById(R.id.epExpo);
        pPrac = (EditText) findViewById(R.id.epPrac);
        pProy = (EditText) findViewById(R.id.epProy);
        bGuardar = (Button) findViewById(R.id.bGuardar);

        Bundle extras = getIntent().getExtras();

        pExpo.setText(String.valueOf(extras.getInt("pExpo")));
        pPrac.setText(String.valueOf(extras.getInt("pPrac")));
        pProy.setText(String.valueOf(extras.getInt("pProy")));

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("npExpo",pExpo.getText().toString());
                intent.putExtra("npPrac",pPrac.getText().toString());
                intent.putExtra("npProy",pProy.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
