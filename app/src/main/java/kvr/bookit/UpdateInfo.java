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


public class UpdateInfo extends DialogFragment {

    ////interface to handle the Events
    interface UpdateDialogListener{

        void onUpdateButtonClick(DialogFragment dialog);
        //void onCancelButtonClick(DialogFragment dialog);

    }

    //create an Instance to deliever the action
    UpdateDialogListener updateListener;
    Context context;

    // Override the Fragment.onAttach() method to instantiate the SetPasswordDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the updateListener so we can send events to the host
            updateListener = (UpdateDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement UpdateDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.update_info, null))

                // Add action buttons
                .setPositiveButton(R.string.tv_Update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        updateListener.onUpdateButtonClick(UpdateInfo.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UpdateInfo.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }


}
