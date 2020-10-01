package com.example.oma.api_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText apellido,nombre,sueldo;
    Button btninsertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apellido=findViewById(R.id.etapellido);
        nombre=findViewById(R.id.etnombre);
        sueldo=findViewById(R.id.etsueldo);

        btninsertar=findViewById(R.id.btinsertar);
        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar();
            }
        });
    }

    private void insertar() {
        final String nombre1=nombre.getText().toString().trim();
        final String apellido1=apellido.getText().toString().trim();
        final String sueldo1=sueldo.getText().toString().trim();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        if (nombre1.isEmpty()){
            nombre.setError("Complete los campos");
        }
        else if (apellido1.isEmpty()){
            apellido.setError("Complete los campos");
        }
        else if (sueldo1.isEmpty()){
            sueldo.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos insertados")) {
                        Toast.makeText(MainActivity.this, "Datos ingresados", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String ,String>paramo= new HashMap<String ,String>();
                    paramo.put("nombre",nombre1);
                    paramo.put("apellido",apellido1);
                    paramo.put("sueldo",sueldo1);
                    return paramo;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
    }
}