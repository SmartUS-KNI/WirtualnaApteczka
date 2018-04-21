package smartcity.kni.wirtualnaapteczka.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


import smartcity.kni.wirtualnaapteczka.R;

public class MonthlyDoseAdjustmentDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.monthly_dosage_adjustment_layout, null);
        //view = addDaysCheckBoxes(view);
        builder
                .setTitle(R.string.pickDaysOfMonth)
                .setPositiveButton(R.string.save, (dialogInterface, i) -> {

                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {

                })
                .setView(view)
        ;
        return builder.create();
    }

    private View addDaysCheckBoxes(View v) {

/*
        GridLayout gridLayout = (GridLayout) ((ViewGroup) v).getChildAt(0);
        int noColumns = 3, noRows = 11, noDays = 31;

        gridLayout.setColumnCount(3);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (int j = 0; j < noRows - 1; j++)
            for (int i = 0; i < noColumns - 1; i++) {
                if (noDays == 0)
                    break;
                CheckBox temp = new CheckBox(getContext());
                temp.setText("day");
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(j));
temp.setLayoutParams(params);
                gridLayout.addView(temp);
                noDays--;
            }

*/
        return v;
    }
}
