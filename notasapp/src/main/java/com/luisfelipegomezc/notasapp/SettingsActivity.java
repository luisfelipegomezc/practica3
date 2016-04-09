package com.luisfelipegomezc.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {


    EditText pExpo, pPrac, pProy;
    Button bGuardar, bBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        pExpo = (EditText) findViewById(R.id.epExpo);
        pPrac = (EditText) findViewById(R.id.epPrac);
        pProy = (EditText) findViewById(R.id.epProy);
        bGuardar = (Button) findViewById(R.id.bGuardar);
        bBorrar = (Button) findViewById(R.id.bBorrar);

        Bundle extras = getIntent().getExtras();

        pExpo.setText(String.valueOf(extras.getInt("pExpo")));
        pPrac.setText(String.valueOf(extras.getInt("pPrac")));
        pProy.setText(String.valueOf(extras.getInt("pProy")));


        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pExpo.setText(String.valueOf(""));
                pPrac.setText(String.valueOf(""));
                pProy.setText(String.valueOf(""));

                Toast toast = Toast.makeText(getApplicationContext(), "Ingrese Nuevos Porcentajes", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        bGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Double total = Double.valueOf(0);
                if ((pExpo.getText().toString().equals("")) || (pPrac.getText().toString().equals("")) || (pExpo.getText().toString().equals(""))) {

                    Toast toast = Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    total = Double.parseDouble(pExpo.getText().toString()) + Double.parseDouble(pPrac.getText().toString()) + Double.parseDouble(pProy.getText().toString());
                    if (total == 100) {
                        Intent intent = new Intent();
                        intent.putExtra("npExpo", pExpo.getText().toString());
                        intent.putExtra("npPrac", pPrac.getText().toString());
                        intent.putExtra("npProy", pProy.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "La suma del porcentaje es Incorrecta : " + total, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }
}
