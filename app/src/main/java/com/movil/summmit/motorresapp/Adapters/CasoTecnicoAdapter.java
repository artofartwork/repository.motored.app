package com.movil.summmit.motorresapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Listeners.OnItemClickListener;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.R;

import java.util.List;

/**
 * Created by cgonzalez on 12/01/2018.
 */

public class CasoTecnicoAdapter extends RecyclerView.Adapter<CasoTecnicoAdapter.ViewHolder> {
    public List<CasoTecnico> players;
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView  caso, fecha, marca, modelo, criticidad;
        public ImageView ivCheckBox;
        public View view;
        public ViewHolder(View  v) {
            super(v);
            this.view = v;
            caso = (TextView) view.findViewById(R.id.caso);
            fecha = (TextView) view.findViewById(R.id.fecha);
            marca = (TextView) view.findViewById(R.id.marca);
            modelo = (TextView) view.findViewById(R.id.modelo);
            criticidad = (TextView)view.findViewById(R.id.criticidad);
            ivCheckBox = (ImageView)view.findViewById(R.id.iv_check_box);

        }
    }

    public CasoTecnicoAdapter(List<CasoTecnico> players) {
        this.players = players;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_lista_casotecnico, parent, false);

        //return new CasoTecnicoAdapter.CasoTecnicoViewHolder(itemView);
        ViewHolder vh = new ViewHolder(itemView);
        return  vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        CasoTecnico player = players.get(position);
        holder.caso.setText(player.getIdCasoTecnico() + "");
        holder.fecha.setText(player.getFechaFalla().toString()); //creo q falta ese campo
        holder.marca.setText(player.getNombreMarca());
        holder.modelo.setText(player.getNombreModelo());
        holder.criticidad.setText(player.getCriticidad());//creo q falta ese campo

        if (player.getSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);
        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                 //   Log.v("ADAPTER", "iviPhoto "+holder.iviPhoto);
                   // ViewCompat.setTransitionName(holder.iviPhoto, "iviPhoto");
                    onItemClickListener.onClikListener(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void updateRecords(List<CasoTecnico> entidades){
        this.players = entidades;
        notifyDataSetChanged();
    }


}
