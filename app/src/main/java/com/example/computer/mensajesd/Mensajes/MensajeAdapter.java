package com.example.computer.mensajesd.Mensajes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.computer.mensajesd.R;

import java.util.List;


public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajesViewHolder> {
    private List<MensajeDeTexto> mensajeDeTextos;

    public MensajeAdapter(List<MensajeDeTexto> mensajeDeTextos) {
        this.mensajeDeTextos = mensajeDeTextos;
    }

    @Override
    public MensajesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mensajes, parent, false);

        return new MensajesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MensajesViewHolder holder, int position) {

        RelativeLayout.LayoutParams rl = (RelativeLayout.LayoutParams) holder.cardView.getLayoutParams();
        FrameLayout.LayoutParams fl = (FrameLayout.LayoutParams) holder.mensajeBG.getLayoutParams();
        if (mensajeDeTextos.get(position).getTipoMensaje() == 1) {
            holder.mensajeBG.setBackgroundResource(R.drawable.in_message_bg);
            rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            fl.gravity = Gravity.RIGHT;
        } else if (mensajeDeTextos.get(position).getTipoMensaje() == 2) {
            holder.mensajeBG.setBackgroundResource(R.drawable.out_message_bg);
            rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            fl.gravity = Gravity.LEFT;
        }

        holder.cardView.setLayoutParams(rl);


        holder.TvMensaje.setText(mensajeDeTextos.get(position).getMensaje());
        holder.TvHora.setText(mensajeDeTextos.get(position).getHoraDelMensaje());
    }

    @Override
    public int getItemCount() {
        return mensajeDeTextos.size();
    }

    static class MensajesViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        LinearLayout mensajeBG;
        TextView TvMensaje;
        TextView TvHora;

        MensajesViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cvMensaje);
            mensajeBG = itemView.findViewById(R.id.mensajeBG);
            TvMensaje = itemView.findViewById(R.id.msTexto);
            TvHora = itemView.findViewById(R.id.msHora);
        }
    }
}

