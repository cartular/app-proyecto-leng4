package com.varasccatalina.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }
    // Define las consultas SQL para la creación de tablas
    private static final String CREATE_TABLE_DIA = "CREATE TABLE dia (id INTEGER PRIMARY KEY, nombre TEXT)";

    private static final String CREATE_TABLE_CLASE = "CREATE TABLE clase (id INTEGER PRIMARY KEY, nombre TEXT, tipo TEXT, horario_inicio TEXT, horario_fin TEXT, aula TEXT, edificio TEXT, profesor TEXT, dia_id INTEGER, FOREIGN KEY (dia_id) REFERENCES dia(id))";


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL(CREATE_TABLE_DIA);
        MyDB.execSQL(CREATE_TABLE_CLASE);

    }
    public Cursor getDias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "nombre"};

        return db.query("dia", projection, null, null, null, null, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("DROP TABLE IF EXISTS dia");
        MyDB.execSQL("DROP TABLE IF EXISTS clase");

    }
    public void insertDataIntoDia() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Insertar fila 1
        values.put("nombre", "Lunes");
        db.insert("dia", null, values);

        // Insertar fila 2
        values.clear();
        values.put("nombre", "Martes");
        db.insert("dia", null, values);

        // Insertar fila 3
        values.clear();
        values.put("nombre", "Miércoles");
        db.insert("dia", null, values);

        // Insertar fila 4
        values.clear();
        values.put("nombre", "Jueves");
        db.insert("dia", null, values);

        // Insertar fila 5
        values.clear();
        values.put("nombre", "Viernes");
        db.insert("dia", null, values);

        // Insertar fila 6
        values.clear();
        values.put("nombre", "Sábado");
        db.insert("dia", null, values);

        // Cierra la base de datos
        db.close();
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public long insertClase(String nombre, String tipo, String horarioInicio, String horarioFin, String aula, String edificio, String profesor, long dia_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("tipo", tipo);
        values.put("horario_inicio", horarioInicio);
        values.put("horario_fin", horarioFin);
        values.put("aula", aula);
        values.put("edificio", edificio);
        values.put("profesor", profesor);
        values.put("dia_id", dia_id);

        // Insert the new row and return the primary key value
        return db.insert("clase", null, values);
    }

    // Método para obtener todas las clases para un día específico
    public Cursor getClasesByDia(long diaId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"nombre", "tipo", "horario_inicio", "horario_fin", "aula", "edificio", "profesor"};
        String selection = "dia_id=?";
        String[] selectionArgs = {String.valueOf(diaId)};

        return db.query("clase", projection, selection, selectionArgs, null, null, null);
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}


