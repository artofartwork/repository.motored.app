package com.movil.summmit.motorresapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.R;

import java.util.List;

/**
 * Created by cgonzalez on 12/01/2018.
 */

public class AnalisisCausaFallaAdapter extends RecyclerView.Adapter<AnalisisCausaFallaAdapter.AnalisisCausaFallViewHolder> {
    public List<InformeTecnicoFalla> players;

    public class AnalisisCausaFallViewHolder extends RecyclerView.ViewHolder {
        private TextView sistema, modofalla ,nrocaso;
        private CheckBox scanner, aceite, combustible;

        public AnalisisCausaFallViewHolder(View view) {
            super(view);
            sistema = (TextView) view.findViewById(R.id.sistemafalla);
            modofalla = (TextView) view.findViewById(R.id.modofalla);
            nrocaso = (TextView) view.findViewById(R.id.nrocaso);

            scanner = (CheckBox) view.findViewById(R.id.scanner);
            aceite = (CheckBox) view.findViewById(R.id.muestraceite);
            combustible = (CheckBox) view.findViewById(R.id.muestracombustible);
        }
    }

    public AnalisisCausaFallaAdapter(List<InformeTecnicoFalla> players) {
        this.players = players;
    }

    @Override
    public AnalisisCausaFallaAdapter.AnalisisCausaFallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_lista_analisis_causa_falla, parent, false);

        return new AnalisisCausaFallaAdapter.AnalisisCausaFallViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnalisisCausaFallViewHolder holder, int position) {

        InformeTecnicoFalla player = players.get(position);
        holder.sistema.setText(player.getNombreSistema());
        holder.modofalla.setText(player.getNombreModoFalla());
        holder.nrocaso.setText(player.getNroCaso());
        holder.scanner.setChecked(player.getScanner());
        holder.aceite.setChecked(player.getAceite());
        holder.combustible.setChecked(player.getCombustible());

    }


    @Override
    public int getItemCount() {
        return players.size();
    }



}
