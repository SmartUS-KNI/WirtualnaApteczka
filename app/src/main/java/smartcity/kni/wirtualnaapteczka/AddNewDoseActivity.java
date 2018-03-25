package smartcity.kni.wirtualnaapteczka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class AddNewDoseActivity extends AppCompatActivity {

    private enum dialogType{
        CALENDAR(0),TIME(1);
        private final int value;

        dialogType(int value){
            this.value = value;
        }
    }

    /*** Calendar helpful when we want use calendar, or timer, and we want to start today*/
    final Calendar cal = Calendar.getInstance();

    /*** we have to add this data to does in our databases.*/
    java.util.Date date = new Date();


    EditText dateEditText;
    EditText timeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        final Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        setContentView(R.layout.activity_add_new_dose);

        ImageButton dataButton = (ImageButton) findViewById(R.id.add_data_button);
        ImageButton hourButton = (ImageButton) findViewById(R.id.add_hour_button);
        Button confirmButton = (Button) findViewById(R.id.confirm_button);

        dateEditText = (EditText) findViewById(R.id.date_EditText);
        dateEditText.setFocusable(false);

        timeEditText = (EditText) findViewById(R.id.timeEditText);
        timeEditText.setFocusable(false);

        final LinearLayout adjustContainer = (LinearLayout) findViewById(R.id.adjust_Container);

        CheckBox adjustCheckBox = (CheckBox)findViewById(R.id.adjust_CheckBox);

        adjustContainer.setVisibility(View.GONE);
        adjustCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    adjustContainer.setVisibility(View.VISIBLE);
                else
                    adjustContainer.setVisibility(View.GONE);
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogType.TIME.value);
            }
        });

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogType.CALENDAR.value);
            }
        });

        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogType.CALENDAR.value);
            }
        });
        hourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(dialogType.TIME.value);
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dose dose = new Dose();
                dose.setTime(date);
                //does.setMedicine(medicine);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == dialogType.CALENDAR.value){
            return new DatePickerDialog(this, dPickerListener,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        } if(id == dialogType.TIME.value){
            return new TimePickerDialog(this,tPickerListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            date.setDate(i2);
            date.setYear(i);
            date.setMonth(i1+1);
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
}
