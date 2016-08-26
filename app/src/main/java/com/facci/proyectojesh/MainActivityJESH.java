package com.facci.proyectojesh;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityJESH extends AppCompatActivity {

    DBHelper dbSQLite;

    EditText txtnombre,txtapellido,txtAnonacimiento,txtrecintoElec,Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jesh);
        dbSQLite = new DBHelper(this);
    }
    public void InsetarClick(View v) {
        txtnombre = (EditText) findViewById(R.id.txtNombre);
        txtapellido = (EditText) findViewById(R.id.txtApellido);
        txtrecintoElec = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAnonacimiento = (EditText) findViewById(R.id.txtanoNacimiento);

        boolean Guardadatos = dbSQLite.Insertar(txtnombre.getText().toString(),txtapellido.getText().toString(),txtrecintoElec.getText().toString(),Integer.parseInt(txtAnonacimiento.getText().toString()));

        if (Guardadatos) {
            Toast.makeText(MainActivityJESH.this, "Datos Ingresados", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(MainActivityJESH.this,"Ocurrio un error datos no ingresados",Toast.LENGTH_SHORT).show();}
    }
    public void VerTodosClick(View v) {
        Cursor res = dbSQLite.VerTodos();

        if (res.getCount() == 0) {
            Mensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año de Nacimiento : "+res.getInt(4)+"\n\n");
        }
        Mensaje("Registros",buffer.toString());
    }

    private void Mensaje (String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void ModificarClick (View v) {
        txtnombre = (EditText) findViewById(R.id.txtNombre);///OBTIENE LOS ID
        txtapellido = (EditText) findViewById(R.id.txtApellido);
        txtrecintoElec = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAnonacimiento = (EditText) findViewById(R.id.txtanoNacimiento);
        Id = (EditText) findViewById(R.id.txtID);


        boolean ModificandoDatos = dbSQLite.ModificarRegistro(Id.getText().toString(),txtnombre.getText().toString(),txtapellido.getText().toString(),txtrecintoElec.getText().toString(),Integer.parseInt(txtAnonacimiento.getText().toString()));
        if(ModificandoDatos)
            Toast.makeText(MainActivityJESH.this,"Datos Modificados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityJESH.this,"Lo sentimos ocurrió un error",Toast.LENGTH_SHORT).show();

    }
    public void EliminarClick (View v) {
        Id = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLite.Eliminar(Id.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityJESH.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJESH.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }
    }
}
