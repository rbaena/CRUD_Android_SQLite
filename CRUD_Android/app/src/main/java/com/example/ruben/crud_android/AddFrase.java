package com.example.ruben.crud_android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.os.Handler;


public class AddFrase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frase);

        FloatingActionButton faba = (FloatingActionButton) findViewById(R.id.faba);
        faba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener los datos de los campos
                EditText fraseField = (EditText) findViewById(R.id.fraseField);
                EditText autorField = (EditText) findViewById(R.id.autorField);

                //Nuevo Intent con Extras
                Intent backData = new Intent();
                backData.putExtra("frase", fraseField.getText().toString());
                backData.putExtra("autor", autorField.getText().toString());

                //Enviar la información
                setResult(RESULT_OK, backData);

                Snackbar.make(view, "Nueva Frase registrada satisfactoriamente!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                fraseField.setText("");
                autorField.setText("");
                //Ordenar cursor - focus
                autorField.requestFocus();
                //Esperar
                delay(100000);

                //Terminar la actividad Nueva_Frase
                //finish();
            }
        });
    }

    public void delay(int seconds) {
        final int milliseconds = seconds * 1000;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("XXX");                 //add your code here
                    }
                }, milliseconds);
            }
        });
    }
}
