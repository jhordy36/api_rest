package com.example.oma.api_rest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    Adaptador adaptador;
    public static ArrayList<Users>users=new ArrayList<>();

    String url = "https://supermundane-spoon.000webhostapp.com/Personas/mostrar.php";
    Users usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        listView = findViewById(R.id.);
        adaptador = new Adaptador(this,users);
        listView.setAdapter(adaptador);
        mostrarDatos();
    }

    public void mostrarDatos(){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                users.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("persona");
                    if (success.equals("1")){
                        for (int i=1; i<jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String apellido = object.getString("apellido");
                            String nombre = object.getString("nombre");
                            String sueldo = object.getString("sueldo");

                            usuarios = new Users(id, nombre, apellido, sueldo);
                            users.add(usuarios);
                            adaptador.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void btnApp(View view){

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}