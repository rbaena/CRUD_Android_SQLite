package com.example.ruben.locelebre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //Código de envío al formulario de captura de datos
    public final static int ADD_REQUEST_CODE = 1;

    //Atributos para datos
    private FrasesDataSource dataSource;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Crear nuevo objeto FrasesDataSource
        dataSource = new FrasesDataSource(this);

        //Iniciando el nuevo Adaptador
        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                dataSource.getAllFrases(),
                new String[]{FrasesDataSource.ColumnFrases.BODY_FRASE, FrasesDataSource.ColumnFrases.AUTOR_FRASE},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER

        );

        //Configurando ListView al objeto con los elementos
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciando la actividad form_main
                Intent intent = new Intent(MainActivity.this, Form.class);

                //Inicio de la actividad esperando un resultado
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

       return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                //Insertando el registro con los datos del formulario
                String frase = data.getStringExtra("frase");
                String autor = data.getStringExtra("autor");

                dataSource.saveFraseRow(frase, autor);
                //Refrescando la lista manualmente
                adapter.changeCursor(dataSource.getAllFrases());

            }
        }

    }

}
