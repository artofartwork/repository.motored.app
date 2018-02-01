package com.movil.summmit.motorresapp.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.movil.summmit.motorresapp.Listeners.OnAntecedenteListener;
import com.movil.summmit.motorresapp.R;


/**
 * Created by TELEFONICA on 11/01/2018.
 */

public class DialogAntecedente extends DialogFragment {

    private EditText comentario;
    private OnAntecedenteListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_antecedente, null);

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

                        String mesage =  comentario.getText().toString();
                        enviaMessage(mesage);
                    }
                });

        comentario = (EditText) view.findViewById(R.id.comentarioAntecedente);

        return builder.create();
    }

    public void enviaMessage(String msg)
    {
        listener = (OnAntecedenteListener)getActivity();
        listener.recibiryEnviardesdeFragment(msg);
    }
}
