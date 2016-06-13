package com.example.ruben.locelebre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Ruben on 12/06/2016.
 */
public class FrasesDataSource {

    //Metainformación de la base de datos
    public static final String FRASES_TABLE_NAME = "Frases";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla Frases
    public static class ColumnFrases{
        public static final String ID_FRASE = BaseColumns._ID;
        public static final String BODY_FRASE = "frase";
        public static final String AUTOR_FRASE = "autor";
    }

    //Script de Creación de la tabla Frases
    public static final String CREATE_FRASES_SCRIPT =
            "create table "+FRASES_TABLE_NAME+"(" +
                    ColumnFrases.ID_FRASE+" "+INT_TYPE+" primary key autoincrement," +
                    ColumnFrases.BODY_FRASE+" "+STRING_TYPE+" not null," +
                    ColumnFrases.AUTOR_FRASE+" "+STRING_TYPE+" not null)";

    //Scripts de inserción por defecto
    public static final String INSERT_FRASES_SCRIPT =
            "insert into "+FRASES_TABLE_NAME+" values(" +
                    "null," +
                    "\"La vida es dura y las noches son largas.\"," +
                    "\"Luis Cobo Campo\")," +
                    "(null," +
                    "\"El ignorante afirma, el sabio duda y reflexiona.\"," +
                    "\"Aristóteles\")," +
                    "(null," +
                    "\"El conocimiento solo es bueno si es compartido con otros.\"," +
                    "\"Luis Cobo Campo\")," +
                    "(null," +
                    "\"La satisfacción de aprender queda impregnada por siempre.\"," +
                    "\"Rubén Baena Navarro\")," +
                    "(null," +
                    "\"Donde mora la libertad, allí está mi patria.\"," +
                    "\"Benjamin Franklin\")";


    //Variables para manipulación de datos
    private FrasesReaderDbHelper openHelper;
    private SQLiteDatabase database;

    public FrasesDataSource(Context context) {
        //Creando una instancia hacia la base de datos
        openHelper = new FrasesReaderDbHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void saveFraseRow(String frase,String autor){
        //Nuestro contenedor de valores
        ContentValues values = new ContentValues();

        //Seteando body y author
        values.put(FrasesDataSource.ColumnFrases.BODY_FRASE, frase);
        values.put(FrasesDataSource.ColumnFrases.AUTOR_FRASE, autor);

        //Insertando en la base de datos
        database.insert(FRASES_TABLE_NAME, null, values);
    }

    public Cursor getAllFrases(){
        //Seleccionamos todas las filas de la tabla Frases
        return database.rawQuery(
                "select * from " + FRASES_TABLE_NAME, null);
    }

}
