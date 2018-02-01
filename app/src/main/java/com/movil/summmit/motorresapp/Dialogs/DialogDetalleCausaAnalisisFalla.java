package com.movil.summmit.motorresapp.Dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;


import com.movil.summmit.motorresapp.Listeners.OnAntecedenteListener;
import com.movil.summmit.motorresapp.R;

/**
 * Created by cgonzalez on 12/01/2018.
 */

public class DialogDetalleCausaAnalisisFalla extends DialogFragment{

   // private EditText comentario;
    private OnAntecedenteListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_detallecausa_falla, null);

        builder.setView(view)
                .setTitle("Antecedente")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       // String mesage =  comentario.getText().toString();
                        //enviaMessage(mesage);
                    }
                });

        //comentario = (EditText) view.findViewById(R.id.comentarioAntecedente);
        Resources res = getResources();
        TabHost tabs=(TabHost)view.findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Trab. Diagnostico");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Causa Falla");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Trab. Correctivo");
        tabs.addTab(spec);

        tabs.setCurrentTab(1);

        return builder.create();
    }

    public void enviaMessage(String msg)
    {
        listener = (OnAntecedenteListener)getActivity();
        listener.recibiryEnviardesdeFragment(msg);
    }
}
