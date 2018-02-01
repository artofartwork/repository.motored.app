package com.movil.summmit.motorresapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgonzalez on 11/01/2018.
 */

public class AdapterItem extends BaseAdapter {

    protected Context context;
    protected List<Empleado> items;

    public AdapterItem(Context context, List<Empleado> lsNoteEntities) {
        this.context = context;
        this.items = lsNoteEntities;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.item_lista_tecnicos, null);

            holder.txtName = (TextView) convertView.findViewById(R.id.txvTecnico);
            holder.chkTick = (CheckBox) convertView.findViewById(R.id.chktec);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Empleado dir = items.get(position);

        final int pos = position;
        holder.txtName.setText(dir.getNombre());
        holder.chkTick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        return convertView;
    }
    static class ViewHolder {
        TextView txtName;
        CheckBox chkTick;
    }
}
