package kvr.bookit;

/**
 * Created by Kasi_Visu on 4/8/2018.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;



public class AddDialog extends DialogFragment{

    ////interface to handle the Events
    interface AddDialogListener{

        void onSaveButtonClick(DialogFragment dialog);
        //void onCancelButtonClick(DialogFragment dialog);

    }

    //create an Instance to deliever the action
    AddDialogListener addListener;
    Context context;

    // Override the Fragment.onAttach() method to instantiate the SetPasswordDialogListener
    @Override
    public void onAttach (Activity activity)
    {
        super.onAttach (activity);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the SetPasswordDialogListener so we can send events to the host
            addListener = (AddDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddDialogListener");
        }
    }
    //END

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.ins_form, null))

                // Add action buttons
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addListener.onSaveButtonClick(AddDialog.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
