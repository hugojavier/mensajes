package com.example.computer.mensajesd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private static String IP = "http://medicalchat.000webhostapp.com/ArchivosPHP/Login_GETID.php?id=";
    private EditText eTusuario;
    private EditText eTcontraseña;
    private Button bTingresar;
    private VolleyRP volley;
    private RequestQueue mRequest;
    private String USER = "";
    private String PASSWORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        eTusuario = findViewById(R.id.eTusuario);
        eTcontraseña = findViewById(R.id.eTcontraseña);

        bTingresar = findViewById(R.id.bTingresar);

        bTingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificarLogin(eTusuario.getText().toString().toLowerCase(), eTcontraseña.getText().toString().toLowerCase());
            }
        });
    }

    public void VerificarLogin(String user, String Password) {
        USER = user;
        PASSWORD = Password;
        SolicitudJSON(IP + user);


    }

    public void SolicitudJSON(String URL) {
        JsonObjectRequest solicitud = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject datos) {
                VerificarPassword(datos);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    public void VerificarPassword(JSONObject datos) {
        try {
            String estado = datos.getString("resultado");
            if (estado.equals("CC")) {
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));
                String nombre = Jsondatos.getString("id");
                String clave = Jsondatos.getString("password");
                Toast.makeText(this, nombre + clave, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
        }
    }

}
