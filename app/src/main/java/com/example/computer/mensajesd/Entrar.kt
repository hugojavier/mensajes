package com.example.computer.mensajesd

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class Entrar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrar)


        var editText_usuario = findViewById<EditText>(R.id.editText_usuario) as EditText
        var boton_entrar = findViewById<Button>(R.id.boton_entrar) as Button

        boton_entrar.setOnClickListener {

            val intent = Intent(this@Entrar, VerContactos::class.java)
            Toast.makeText(this@Entrar, "Cargando   ", Toast.LENGTH_SHORT).show()
            var usuario :String = editText_usuario.text.toString()
            val b :Bundle = Bundle()
            b.putString("cuenta", usuario)
            intent.putExtras(b)
            startActivity(intent)
        }

    }
}