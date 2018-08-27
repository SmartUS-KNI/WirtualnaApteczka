package smartcity.kni.wirtualnaapteczka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewDoseActivity extends AppCompatActivity {

    EditText doseCountEditText;
    Spinner dosePeriodSpinner;
    Button dosePeriodAdjustButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dose);

        setTitle(getResources().getString(R.string.title_activity_add_new_dose));

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicine_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initViews() {
        doseCountEditText = (EditText) findViewById(R.id.count_of_dose);
        dosePeriodSpinner = (Spinner) findViewById(R.id.dose_period_spinner);
        dosePeriodAdjustButton = (Button) findViewById(R.id.adjust_button);
    }
}
