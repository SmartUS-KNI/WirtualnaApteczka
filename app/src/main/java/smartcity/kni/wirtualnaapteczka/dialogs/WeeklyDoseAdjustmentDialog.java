package smartcity.kni.wirtualnaapteczka.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.listeners.AdjustmentDialogListener;

public class WeeklyDoseAdjustmentDialog extends DialogFragment {
    private ArrayList mItems;
    AdjustmentDialogListener adjustmentDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mItems = new ArrayList();

        try {
            adjustmentDialogListener = (AdjustmentDialogListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AdjustmentDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle(R.string.pickDaysOfWeek)
                .setPositiveButton(R.string.save, (dialogInterface, i) -> {

                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                })
                .setMultiChoiceItems(R.array.daysOfWeek, null,
                        (dialog, which, isChecked) -> {
                            if (isChecked)
                                mItems.add(which);
                            else
                                mItems.remove(Integer.valueOf(which));
                        });

        return builder.create();
    }
}
