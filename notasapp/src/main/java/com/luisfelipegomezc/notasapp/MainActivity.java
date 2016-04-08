package com.luisfelipegomezc.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, ePrac, eProy, eNotaF;
    Button bCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eExpo = (EditText) findViewById(R.id.eExpo);
        ePrac = (EditText) findViewById(R.id.ePrac);
        eProy = (EditText) findViewById(R.id.eProy);
        eNotaF = (EditText) findViewById(R.id.eNotaFial);
        bCalcular = (Button) findViewById(R.id.bCalc);

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double notaF;
                notaF = Double.parseDouble(eExpo.getText().toString())*15/100 + Double.parseDouble(ePrac.getText().toString())*50/100 +
                        Double.parseDouble(eProy.getText().toString())*35/100;
                eNotaF.setText(String.valueOf(notaF));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mConfig){
            //Toast.makeText(MainActivity.this, "presionó menu Configuracion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("pExpo", 15);
            intent.putExtra("pPrac", 50);
            intent.putExtra("pProy", 35);
            startActivityForResult(intent, 1234);
            }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String exp, pro, pra;
        if (requestCode==1234 && resultCode==RESULT_OK) {
            exp = data.getExtras().getString("npExpo" );
            pro = data.getExtras().getString("npProy" );
            pra = data.getExtras().getString("npPrac");

            Toast.makeText(this, "Nuevos porcentajes:  Exposiciones " + exp + " proyecto " + pro +
                    "practicas" + pra, Toast.LENGTH_SHORT).show();
        }
    }
}
