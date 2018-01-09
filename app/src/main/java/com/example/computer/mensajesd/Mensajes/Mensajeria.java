package com.example.computer.mensajesd.Mensajes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.computer.mensajesd.R;

import java.util.ArrayList;
import java.util.List;


public class Mensajeria extends AppCompatActivity {

    private RecyclerView rv;
    private List<MensajeDeTexto> mensajeDeTextos = new ArrayList<>();
    private MensajeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);

        rv = findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("emisor " + i);
            mensajeDeTextoAuxiliar.setTipoMensaje(1);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:30");
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("receptor " + i);
            mensajeDeTextoAuxiliar.setTipoMensaje(2);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:30");
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        adapter = new MensajeAdapter(mensajeDeTextos);
        rv.setAdapter(adapter);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void CreateMensaje(String mensaje) {
        MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
        mensajeDeTextoAuxiliar.setId("0");
        mensajeDeTextoAuxiliar.setMensaje(mensaje);
        mensajeDeTextoAuxiliar.setTipoMensaje(1);
        mensajeDeTextoAuxiliar.setHoraDelMensaje("10:30");
        mensajeDeTextos.add(mensajeDeTextoAuxiliar);
    }
}
