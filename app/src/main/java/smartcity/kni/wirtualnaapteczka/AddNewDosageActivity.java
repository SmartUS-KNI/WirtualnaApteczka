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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.SQLClientInfoException;
import java.util.Calendar;
import java.util.Date;
import smartcity.kni.wirtualnaapteczka.Dose;
import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class AddNewDosageActivity extends AppCompatActivity {
    static final int CALENDAR_DIALOG_ID = 0;
    static final int HOUR_DIALOG_ID = 1;

    /*** Calendar helpful when we want use calendar, or timer, and we want to start today*/
    final Calendar cal = Calendar.getInstance();

    /*** we have to add this data to does in our databases.*/
    java.util.Date data = new Date();

    TextView dataTextView;
    TextView hourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        final Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        setContentView(R.layout.activity_add_new_dosage);

        Button dataButton = (Button) findViewById(R.id.add_data_button);
        Button hourButton = (Button) findViewById(R.id.add_hour_button);
        Button confirmButton = (Button) findViewById(R.id.confirm_button);

        dataTextView = (TextView) findViewById(R.id.data_textView);
        hourTextView = (TextView) findViewById(R.id.hour_textView);
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


        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(CALENDAR_DIALOG_ID);
            }
        });
        hourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(HOUR_DIALOG_ID);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dose does = new Dose();
                does.setTime(data);
                does.setMedicine(medicine);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == CALENDAR_DIALOG_ID){
            return new DatePickerDialog(this, dpickerlistner,cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        } if(id == HOUR_DIALOG_ID){
            return new TimePickerDialog(this,tPickerListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerlistner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            data.setDate(i2);
            data.setYear(i);
            data.setMonth(i1+1);
            dataTextView.setText(data.getYear() + " / " + data.getMonth() + " / " + data.getDate());
        }
    };

    private TimePickerDialog.OnTimeSetListener tPickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            data.setHours(i);
            data.setMinutes(i1);
            hourTextView.setText(data.getHours() + " : " + data.getMinutes());
        }
    };
}
