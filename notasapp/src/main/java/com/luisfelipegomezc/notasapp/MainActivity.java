package com.luisfelipegomezc.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText eExpo, ePrac, eProy, eNotaF;
    String valPorcentajes = "Cargue Porcentajes en el Menu";
    Button bCalcular, bBorrar;
    int error=0;
    Double pexpo = Double.valueOf(0), pprac = Double.valueOf(0), pproy = Double.valueOf(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(getApplicationContext(), "Favor configure los parcentajes en el Menu", Toast.LENGTH_LONG);
        toast.show();
        TextView texto = (TextView) findViewById(R.id.tValorPorcentajes);
        texto.setText(valPorcentajes);
        eExpo = (EditText) findViewById(R.id.eExpo);
        ePrac = (EditText) findViewById(R.id.ePrac);
        eProy = (EditText) findViewById(R.id.eProy);
        eNotaF = (EditText) findViewById(R.id.eNotaFial);
        bCalcular = (Button) findViewById(R.id.bCalc);
        bBorrar = (Button) findViewById(R.id.bBorrar);



        bBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                eExpo.setText(String.valueOf(""));
                ePrac.setText(String.valueOf(""));
                eProy.setText(String.valueOf(""));
                eNotaF.setText("");
                Toast toast = Toast.makeText(getApplicationContext(), "Ingrese Nuevos datos", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        bCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((eExpo.getText().toString().equals("")) || (ePrac.getText().toString().equals("")) || (eExpo.getText().toString().equals(""))) {
                    eNotaF.setText("Error");
                    Toast toast = Toast.makeText(getApplicationContext(), "No hay datos", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    double notaF;
                    if ((Double.parseDouble(eExpo.getText().toString()) > 5) || (Double.parseDouble(ePrac.getText().toString()) > 5)
                            || (Double.parseDouble(eProy.getText().toString()) > 5)) {
                        eNotaF.setText("error");
                        error = 1;
                    } else {
                        if ((Double.parseDouble(eExpo.getText().toString()) < 0) || (Double.parseDouble(ePrac.getText().toString()) < 0)
                                || (Double.parseDouble(eProy.getText().toString()) < 0)) {
                            eNotaF.setText("Error");
                            error = 2;
                        }
                    }
                    if (error == 0) {
                        notaF = Double.parseDouble(eExpo.getText().toString()) * pexpo + Double.parseDouble(ePrac.getText().toString()) * pprac +
                                Double.parseDouble(eProy.getText().toString()) * pproy;
                        eNotaF.setText(String.valueOf(notaF));
                    }
                    if (error == 1) {
                        Toast toast = Toast.makeText(getApplicationContext(), "La nota no puede ser Mayor que 5", Toast.LENGTH_LONG);
                        toast.show();
                        error = 0;
                    }
                    if (error == 2) {
                        Toast toast = Toast.makeText(getApplicationContext(), "La nota no puede ser Menor que 0", Toast.LENGTH_LONG);
                        toast.show();
                        error = 0;
                    }
                }
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

            pexpo = Double.parseDouble(exp.toString())/100;
            pproy = Double.parseDouble(pro.toString())/100;
            pprac = Double.parseDouble(pra.toString())/100;

            valPorcentajes = "Expo= " +exp+ " Proy= "+pro+ " Prac= "+pra;
            TextView texto = (TextView) findViewById(R.id.tValorPorcentajes);
            texto.setText(valPorcentajes);
            Toast.makeText(this, "Nuevos porcentajes:  Exposiciones " + exp + " proyecto " + pro +
                    " practicas " + pra, Toast.LENGTH_SHORT).show();
        }
    }
}
