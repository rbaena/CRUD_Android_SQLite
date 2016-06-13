package com.example.ruben.locelebre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by Ruben on 12/06/2016.
 */
public class FrasesReaderDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "frases.db";
    public static final int DATABASE_VERSION = 1;

    public FrasesReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la tabla Frases
        db.execSQL(FrasesDataSource.CREATE_FRASES_SCRIPT);
        //Insertar registros iniciales
        db.execSQL(FrasesDataSource.INSERT_FRASES_SCRIPT);

        /*  Nota: Se utiliza execSQL() ya que las sentencias son
            para uso interno y no están relacionadas con entradas
            proporcionadas por los usuarios.
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*  Añade los cambios que se realizarán en el esquema
                en próxima versión
             */
    }

}
