package com.movil.summmit.motorresapp.Dialogs;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;


/**
 * Created by cgonzalez on 12/01/2018.
 */

public class DatePickerFragment extends DialogFragment   {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog builder = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) this, year, month, day);


        return super.onCreateDialog(savedInstanceState);
    }
}