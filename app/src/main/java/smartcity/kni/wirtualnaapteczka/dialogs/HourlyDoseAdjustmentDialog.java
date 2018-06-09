package smartcity.kni.wirtualnaapteczka.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import java.util.ArrayList;

import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.enums.ERegularDoseType;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;
import smartcity.kni.wirtualnaapteczka.listeners.AdjustmentDialogListener;
import smartcity.kni.wirtualnaapteczka.regularConfigurator.RegularConfigurator;

public class HourlyDoseAdjustmentDialog extends DialogFragment {
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_hourly_dosage_adjustment, null);
        builder
                .setTitle(R.string.pickTime)
                .setPositiveButton(R.string.save, (dialogInterface, i) -> {
                    LayoutContentConfig layoutContentConfig = new LayoutContentConfig();
                    layoutContentConfig.addLayoutContentConfigParam(ELayoutContentType.LAYOUT_CONTENT_TYPE_EDITTEXT);
                    LayoutContent content = new LayoutContent(R.layout.dialog_monthly_dosage_adjustment, layoutContentConfig);
                    String regularConfig = RegularConfigurator.generateRegularConfig(ERegularDoseType.MONTHLY, content);

                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                })
                .setView(view);

        return builder.create();
    }

}
