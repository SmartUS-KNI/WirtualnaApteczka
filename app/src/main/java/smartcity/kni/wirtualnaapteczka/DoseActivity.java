package smartcity.kni.wirtualnaapteczka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.enums.ERegularDoseType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.layout.helpers.SpinnerHelper;
import smartcity.kni.wirtualnaapteczka.listeners.AdjustmentDialogListener;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class DoseActivity extends AppCompatActivity implements AdjustmentDialogListener {

    LayoutContent content = null;

    private enum EDialogType {
        CALENDAR(0), TIME(1);
        private final int value;

        EDialogType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /*** Calendar helpful when we want use calendar, or timer, and we want to start today*/
    Calendar cal = Calendar.getInstance();

    /*** we have to add this data to does in our databases.*/
    java.util.Date date = new Date();

    EditText dateEditText;
    EditText timeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dose);

        dateEditText = (EditText) findViewById(R.id.date_EditText);
        timeEditText = (EditText) findViewById(R.id.timeEditText);
        ImageButton dateButton = (ImageButton) findViewById(R.id.add_data_button);
        ImageButton hourButton = (ImageButton) findViewById(R.id.add_hour_button);
        EditText doseCount = (EditText) findViewById(R.id.count_of_dose);
        Button confirmButton = (Button) findViewById(R.id.confirm_button);
        CheckBox adjustCheckBox = (CheckBox) findViewById(R.id.adjust_CheckBox);
        final LinearLayout adjustContainer = (LinearLayout) findViewById(R.id.adjust_Container);
        final Spinner regularDoseType = (Spinner) findViewById(R.id.adjust_spinner);
        final Button adjustButton = (Button) findViewById(R.id.adjust_button);

        dateEditText.setFocusable(false);
        dateEditText.setOnClickListener(view -> showDialog(EDialogType.CALENDAR.value));

        timeEditText.setFocusable(false);
        timeEditText.setOnClickListener(view -> showDialog(EDialogType.TIME.value));

        adjustContainer.setVisibility(View.GONE);
        adjustCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                adjustContainer.setVisibility(View.VISIBLE);
            else
                adjustContainer.setVisibility(View.GONE);
        });

        dateButton.setOnClickListener(view -> showDialog(EDialogType.CALENDAR.value));
        hourButton.setOnClickListener(view -> showDialog(EDialogType.TIME.value));

        SpinnerHelper.fillSpinnerWithStrings(regularDoseType, getString(R.string.period), getStringsFromDoseType());
        regularDoseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                ERegularDoseType doseType = ERegularDoseType.getRegularDoseTypeById((long) parent.getSelectedItemPosition() - 1);
                adjustButton.setEnabled(position == 0 ? false : doseType.isAdjustable());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        adjustButton.setOnClickListener(view -> {
            ERegularDoseType doseType = ERegularDoseType.getRegularDoseTypeById((long) regularDoseType.getSelectedItemPosition() - 1);
            doseType.adjust(getSupportFragmentManager());

        });

        confirmButton.setOnClickListener(view -> {
            try {
                content = ViewManager.getInstance().getContent(findViewById(R.id.dosage_main_container));
            } catch (MissingConverterException e) {
                e.printStackTrace();
            }
            addDoseToDatabase(generateDoseFromContent(content));
            finish();
        });
    }

    private List<String> getStringsFromDoseType() {
        List<String> strings = new ArrayList<>();

        for (ERegularDoseType i : ERegularDoseType.values())
            strings.add(i.getName());

        return strings;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == EDialogType.CALENDAR.value)
            return new DatePickerDialog(
                    this,
                    (datePicker, i, i1, i2) -> {
                        date.setDate(i2);
                        date.setYear(i);
                        date.setMonth(i1 + 1);
                        dateEditText.setText(
                                date.getYear() +
                                        " / " +
                                        date.getMonth() +
                                        " / " +
                                        date.getDate());
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            );

        if (id == EDialogType.TIME.value)
            return new TimePickerDialog(
                    this,
                    (timePicker, i, i1) -> {
                        date.setHours(i);
                        date.setMinutes(i1);
                        timeEditText.setText(
                                date.getHours() +
                                        " : " +
                                        date.getMinutes());
                    },
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
            );
        return null;
    }

    private Dose generateDoseFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Dose dose = new Dose();

        dose.setCount(Integer.parseInt((String) contentMap.get(R.id.count_of_dose)));
        dose.setTime(date);
        dose.setIdMedicine(getIntent().getLongExtra("medicineId", 0));
        if (((CheckBox) findViewById(R.id.adjust_CheckBox)).isChecked()) {
            ERegularDoseType selectedType = ERegularDoseType.values()[(int) contentMap.get(R.id.adjust_spinner) - 1];
            dose.setRegularDose_type(selectedType.getId());
            // dose.setRegularConfig();
        }
        return dose;
    }

    private long addDoseToDatabase(Dose dose) {
        return SQLiteDatabaseHelper.getInstance().insertDose(dose);
    }

    private void updateDoseInDatabase(Dose dose) {
        SQLiteDatabaseHelper.getInstance().updateDose(dose);
    }
}
