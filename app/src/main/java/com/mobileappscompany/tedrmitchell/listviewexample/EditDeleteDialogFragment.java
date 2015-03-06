package com.mobileappscompany.tedrmitchell.listviewexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Android1 on 3/2/2015.
 */
public class EditDeleteDialogFragment extends DialogFragment {

    public static interface IEdit {
        public void onEdit();
    }

    public static interface IDelete {
        public void onDelete();
    }

    IEdit editChoice;
    IDelete deleteChoice;

    public EditDeleteDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("Edit OR Delete");
        alertDialogBuilder.setMessage("Choose whether you want to edit or delete");
        //null should be your on click listener
        alertDialogBuilder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                editChoice.onEdit();
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteChoice.onDelete();
                dialog.dismiss();
            }
        });

        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        deleteChoice = (IDelete) activity;
        editChoice = (IEdit) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        deleteChoice = null;
        editChoice = null;
    }
}
