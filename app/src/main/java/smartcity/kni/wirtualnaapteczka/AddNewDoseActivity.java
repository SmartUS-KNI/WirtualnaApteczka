package smartcity.kni.wirtualnaapteczka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

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
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class AddNewDoseActivity extends AppCompatActivity {

    LayoutContent content;

    private enum EDialogType {
        CALENDAR(0), TIME(1), HOURLY_DOSE(2), WEEKLY_DOSE(3), MONTHLY_DOSE(4);
        private final int value;

        EDialogType(int value) {
            this.value = value;
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

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        final Medicine medicine = new Medicine(); //Dummy Medicine
        //final Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        dateEditText = (EditText) findViewById(R.id.date_EditText);
        timeEditText = (EditText) findViewById(R.id.timeEditText);
        ImageButton dataButton = (ImageButton) findViewById(R.id.add_data_button);
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

        dataButton.setOnClickListener(view -> showDialog(EDialogType.CALENDAR.value));
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
//DO NOTHING
            }
        });

        adjustButton.setOnClickListener(view -> {
            ERegularDoseType doseType = ERegularDoseType.getRegularDoseTypeById((long) regularDoseType.getSelectedItemPosition() - 1);
            doseType.adjust(getSupportFragmentManager());
        });

        confirmButton.setOnClickListener(view -> {
            content = null;
            try {
                content = ViewManager.getInstance().getContent(findViewById(R.id.dosage_container));
            } catch (MissingConverterException e) {
                e.printStackTrace();
            }
            addDoseToDatabase(generateDoseFromContent(content));
        });
    }

    private List<String> getStringsFromDoseType() {
        List<String> strings = new ArrayList<>();

        for (ERegularDoseType i : ERegularDoseType.values())
            strings.add(i.getName());

        return strings;
    }

    private void showAdjustDialog() {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == EDialogType.CALENDAR.value)
            return new DatePickerDialog(this, dPickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        if (id == EDialogType.TIME.value)
            return new TimePickerDialog(this, tPickerListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);


        return null;
    }


    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            date.setDate(i2);
            date.setYear(i);
            date.setMonth(i1 + 1);
            dateEditText.setText(date.getYear() + " / " + date.getMonth() + " / " + date.getDate());
        }
    };

    private TimePickerDialog.OnTimeSetListener tPickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            date.setHours(i);
            date.setMinutes(i1);
            timeEditText.setText(date.getHours() + " : " + date.getMinutes());
        }
    };

    private Dose generateDoseFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Dose dose = new Dose();

        ERegularDoseType selectedType = ERegularDoseType.values()[(int) contentMap.get(R.id.adjust_spinner) - 1];

        dose.setCount((Integer) contentMap.get(R.id.count_of_dose));
        dose.setTime(date);
        dose.setIdMedicine(getIntent().getLongExtra("Id", 0));
        //dose.setRegularDose_type(selectedType.getId());
        //dose.setRegular();
        return dose;
    }

    private long addDoseToDatabase(Dose dose) {
        return SQLiteDatabaseHelper.getInstance().insertDose(dose);
    }

    private void updateDoseInDatabase(Dose dose) {
        SQLiteDatabaseHelper.getInstance().updateDose(dose);
    }
}
