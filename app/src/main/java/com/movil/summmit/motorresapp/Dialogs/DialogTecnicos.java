package com.movil.summmit.motorresapp.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.movil.summmit.motorresapp.Listeners.OnItemTecnicosListener;
import com.movil.summmit.motorresapp.R;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.EmpleadoRepository;

/**
 * Created by cgonzalez on 19/01/2018.
 */

public class DialogTecnicos extends DialogFragment {

    private EmpleadoRepository empleadoRepository;
    private ListView lvTecnicos;
    private OnItemTecnicosListener lister;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_tecnicos, null);

        builder.setView(view)
                .setTitle("Antecedente")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      //  String mesage =  comentario.getText().toString();
                       // enviaMessage(mesage);
                    }
                });
        lvTecnicos = (ListView) view.findViewById(R.id.lstTecnicos);
        lvTecnicos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        return builder.create();



    }
}
