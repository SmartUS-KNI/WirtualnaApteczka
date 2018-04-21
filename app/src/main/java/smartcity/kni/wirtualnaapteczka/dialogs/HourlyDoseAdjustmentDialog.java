package smartcity.kni.wirtualnaapteczka.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import smartcity.kni.wirtualnaapteczka.R;

public class HourlyDoseAdjustmentDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerDialog timePicker = new TimePickerDialog(getActivity(), this, 12, 00, true);
        timePicker.setTitle(getString(R.string.pickTimeIntervalTitle));
        return timePicker;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
        Time timeInterval = new Time(TimeUnit.HOURS.toMillis(hourOfDay) + TimeUnit.MINUTES.toMillis(minutes));
    }
}
