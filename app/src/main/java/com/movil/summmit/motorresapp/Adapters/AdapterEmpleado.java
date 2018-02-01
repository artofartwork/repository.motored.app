package com.movil.summmit.motorresapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.R;

import java.util.List;

/**
 * Created by TELEFONICA on 20/01/2018.
 */

public class AdapterEmpleado extends BaseAdapter {

    Activity activity;
    List<Empleado> users;
    LayoutInflater inflater;

    //short to create constructer using command+n for mac & Alt+Insert for window


    public AdapterEmpleado(Activity activity) {
        this.activity = activity;
    }

    public AdapterEmpleado(Activity activity, List<Empleado> users) {
        this.activity   = activity;
        this.users      = users;
        inflater        = activity.getLayoutInflater();

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.row_lista_tecnicos, viewGroup, false);

            holder = new ViewHolder();

            holder.tvUserName = (TextView)view.findViewById(R.id.tv_user_name);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.iv_check_box);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        Empleado model = users.get(i);

        holder.tvUserName.setText(model.getNombre());

        if (model.getSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);
        return view;
    }

    public void updateRecords(List<Empleado> users){
        this.users = users;
        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView tvUserName;
        ImageView ivCheckBox;

    }
}