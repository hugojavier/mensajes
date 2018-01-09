package com.example.computer.mensajesd.Mensajes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.computer.mensajesd.R;

import java.util.ArrayList;
import java.util.List;


public class Mensajeria extends AppCompatActivity {

    private RecyclerView rv;
    private List<MensajeDeTexto> mensajeDeTextos = new ArrayList<>();
    private MensajeAdapter adapter;
    private Button btEnviarMensaje;
    private EditText eTEscribirMensaje;
    private int TEXT_LINE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajeria);
        mensajeDeTextos = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        btEnviarMensaje = findViewById(R.id.bTenviarMensaje);
        eTEscribirMensaje = findViewById(R.id.eTEscribirMensaje);

        rv = findViewById(R.id.rvMensajes);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("emisor " + i);
            mensajeDeTextoAuxiliar.setTipoMensaje(1);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2" + i);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        for (int i = 0; i < 10; i++) {
            MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
            mensajeDeTextoAuxiliar.setId("" + i);
            mensajeDeTextoAuxiliar.setMensaje("receptor " + i);
            mensajeDeTextoAuxiliar.setTipoMensaje(2);
            mensajeDeTextoAuxiliar.setHoraDelMensaje("10:2" + i);
            mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        }

        adapter = new MensajeAdapter(mensajeDeTextos, this);
        rv.setAdapter(adapter);

        eTEscribirMensaje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (eTEscribirMensaje.getLayout().getLineCount() != TEXT_LINE) {
                    setScrollBarChat();
                    eTEscribirMensaje.getLayout().getLineCount();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateMensaje(eTEscribirMensaje.getText().toString());
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setScrollBarChat();
    }

    public void CreateMensaje(String mensaje) {
        MensajeDeTexto mensajeDeTextoAuxiliar = new MensajeDeTexto();
        mensajeDeTextoAuxiliar.setId("0");
        mensajeDeTextoAuxiliar.setMensaje(mensaje);
        mensajeDeTextoAuxiliar.setTipoMensaje(1);
        mensajeDeTextoAuxiliar.setHoraDelMensaje("10:30");
        mensajeDeTextos.add(mensajeDeTextoAuxiliar);
        adapter.notifyDataSetChanged();
        eTEscribirMensaje.setText("");
        setScrollBarChat();
    }

    public void setScrollBarChat() {
        rv.scrollToPosition(adapter.getItemCount() - 1);
    }
}
