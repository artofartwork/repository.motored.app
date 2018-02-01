package com.movil.summmit.motorresapp.Dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.movil.summmit.motorresapp.Adapters.CasoTecnicoAdapter;
import com.movil.summmit.motorresapp.R;

import java.util.ArrayList;

/**
 * Created by cgonzalez on 12/01/2018.
 */

public class DialogCasosTecnicos extends DialogFragment {

    //private OnItemClickListener listener;
    private CasoTecnicoAdapter adapter;
    private RecyclerView rv;
    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_casostecnico, null);

        builder.setView(view)
                .setTitle("Lista Casos Tecnicos")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // String mesage =  comentario.getText().toString();
                        //enviaMessage(mesage);
                    }
                });


        ArrayList<String> lista = new ArrayList<>();
        lista.add("Caso 1");
        lista.add("Caso 2");
        lista.add("Caso 3");
        lista.add("Caso 4");
        lista.add("Caso 5");
        lista.add("Caso 6");

        ArrayAdapter<String> adapta = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, lista);
        ListView lis = (ListView)view.findViewById(R.id.milista);
        lis.setAdapter(adapta);

        //initLista();
        //comentario = (EditText) view.findViewById(R.id.comentarioAntecedente);
     /*   List<CasoTecnico> players = new ArrayList<>();
        CasoTecnico obj = new CasoTecnico();
        obj.setIdArea(2);
        players.add(obj);
        CasoTecnico obj2 = new CasoTecnico();
        obj.setIdArea(3);
        players.add(obj2);
        CasoTecnico obj3 = new CasoTecnico();
        obj.setIdArea(4);
        players.add(obj3);

        CasoTecnicoAdapter  mAdapter = new CasoTecnicoAdapter(players);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewcasos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);*/

       // recyclerView.addOnItemTouchListener(new RecyclerTouchListener);

        return builder.create();
    }


}
