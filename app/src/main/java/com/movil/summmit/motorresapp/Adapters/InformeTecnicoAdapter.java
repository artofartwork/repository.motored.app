package com.movil.summmit.motorresapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.R;

import java.util.List;

/**
 * Created by cgonzalez on 11/01/2018.
 */

public class InformeTecnicoAdapter extends RecyclerView.Adapter<InformeTecnicoAdapter.InformeTecnicoViewHolder> {
    public List<InformeTecnico> players;

    public class InformeTecnicoViewHolder extends RecyclerView.ViewHolder {
        private TextView cliente, sucursal,modelo, fecha, estado;

        public InformeTecnicoViewHolder(View view) {
            super(view);
            cliente = (TextView) view.findViewById(R.id.cliente);
            sucursal = (TextView) view.findViewById(R.id.sucursal);
            modelo = (TextView) view.findViewById(R.id.modelo);
            fecha = (TextView) view.findViewById(R.id.fecha);
            estado = (TextView) view.findViewById(R.id.estado);
        }
    }

    public InformeTecnicoAdapter(List<InformeTecnico> players) {
        this.players = players;
    }

    @Override
    public InformeTecnicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_lista_informe, parent, false);

        return new InformeTecnicoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InformeTecnicoViewHolder holder, int position) {
        InformeTecnico player = players.get(position);
       /* holder.cliente.setText(player.());
        holder.nationality.setText(player.getNationality());
        holder.club.setText(player.getClub());
        holder.rating.setText(player.getRating().toString());
        holder.age.setText(player.getAge().toString());*/
        holder.cliente.setText(player.getNombreCliente());
        holder.sucursal.setText(player.getNombreSucursal());
        holder.modelo.setText(player.getNombreModelo());
        holder.fecha.setText(player.getAudFechaRegistro().toString());
        holder.estado.setText(player.getEstado());


    }

    @Override
    public int getItemCount() {
        return players.size();
    }



}
