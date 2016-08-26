package com.facci.proyectojesh;

/**
 * Created by Solis on 25/08/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE = "CNE_JESH";
    public static final String TABLA_NOMBRE = "TABLA_VOTANTES_JESH";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOMBRE_JESH";
    public static final String COL_3 = "APELLIDO_JESH";
    public static final String COL_4 = "RECINTO_ELECTORAL_JESH";
    public static final String COL_5 = "AÑO_DE_NACIMIENTO_JESH";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT, %s TEXT, %s INTEGER)",TABLA_NOMBRE, COL_2, COL_3, COL_4, COL_5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLA_NOMBRE));
        onCreate(db);
    }


    public  boolean Insertar (String txtNombre, String txtApellido,String txtRecintoElectoral,int txtanoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,txtNombre);
        contentValues.put(COL_3,txtApellido);
        contentValues.put(COL_4,txtRecintoElectoral);
        contentValues.put(COL_5,txtanoNacimiento);
        long InsertarResultado = db.insert(TABLA_NOMBRE,null,contentValues);

        if (InsertarResultado == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor VerTodos () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return res;
    }

    public boolean ModificarRegistro (String id,String txtNombre,String txtApellido,String txtRecintoElectoral,int txtAnoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,txtNombre);
        contentValues.put(COL_3,txtApellido);
        contentValues.put(COL_4,txtRecintoElectoral);
        contentValues.put(COL_5,txtAnoNacimiento);
        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});
        return true;
    }

    public Integer Eliminar (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{id});
    }
}
