package smartcity.kni.wirtualnaapteczka.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import smartcity.kni.wirtualnaapteczka.R;

public class WeeklyDoseAdjustmentDialog extends DialogFragment {
    private ArrayList mSelectedItems = new ArrayList();

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
                                mSelectedItems.add(which);
                            else
                                mSelectedItems.remove(Integer.valueOf(which));
                        });

        return builder.create();
    }
}
