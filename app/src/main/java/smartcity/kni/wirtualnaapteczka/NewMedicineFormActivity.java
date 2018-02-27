package smartcity.kni.wirtualnaapteczka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.Medicine_Count;
import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.enums.EMedicineType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class NewMedicineFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_medicine_form);

        final LinearLayout countingContainer = (LinearLayout) findViewById(R.id.counting_container);
        CheckBox countCheckBox = (CheckBox) findViewById(R.id.check_counting);
        Spinner medicineType = (Spinner) findViewById(R.id.medicine_type);
        final Spinner medicineTypeUnit = (Spinner) findViewById(R.id.medicine_type_unit);
        Button submitFormButton = (Button) findViewById(R.id.submit_From_New_Medicine_Button);

        countingContainer.setVisibility(View.GONE);
        countCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    countingContainer.setVisibility(View.VISIBLE);
                else
                    countingContainer.setVisibility(View.GONE);
            }
        });

        this.fillSpinnerWithStrings(medicineType, getString(R.string.medicine_type), this.getStringsFromMedicineType());
        medicineType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    fillSpinnerWithStrings(medicineTypeUnit, getString(R.string.medicine_type_unit), EMedicineType.values()[position - 1].getUnits());
                else
                    fillSpinnerWithStrings(medicineTypeUnit, getString(R.string.medicine_type_unit), null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NOTHING TO DO
            }
        });

        submitFormButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View layout = findViewById(R.id.root_From_New_Medicine_Layout);

                LayoutContentConfig contentConfig = new LayoutContentConfig();
                contentConfig.addLayoutContentConfigParam(ELayoutContentType.LAYOUT_CONTENT_TYPE_EDITTEXT);
                contentConfig.addLayoutContentConfigParam(ELayoutContentType.LAYOUT_CONTENT_TYPE_SPINNER);

                LayoutContent content = null;
                try {
                    content = ViewManager.getInstance().getContent(layout, contentConfig);
                } catch (MissingConverterException e) {
                    e.printStackTrace();
                }

                long newMedicineId = -1;
                newMedicineId = SQLiteDatabaseHelper.getInstance().insertMedicine(generateMedicineFromContent(content));
                if (newMedicineId != -1) {
                    Toast.makeText(getApplicationContext(), R.string.add_medicine_success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.add_medicine_failure, Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private Medicine generateMedicineFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Medicine medicine = new Medicine();

        medicine.setName((String) contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        medicine.setDescription((String) contentMap.get(R.id.description_Of_New_Medicine_EditText));
        medicine.setEAN((String) contentMap.get(R.id.barcode_From_New_Medicine_EditText));
        medicine.setMedicine_Count(this.generateMedicineCountFromContent(content));

        return medicine;
    }

    private Medicine_Count generateMedicineCountFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Medicine_Count medicineCount = new Medicine_Count();

        EMedicineType selectedType = EMedicineType.values()[(int) contentMap.get(R.id.medicine_type) - 1];

        medicineCount.setCount(Double.parseDouble((String) contentMap.get(R.id.count)));
        medicineCount.setMedicineType(selectedType.getId());
        medicineCount.setMedicineTypeUnit((int) contentMap.get(R.id.medicine_type_unit) - 1);

        return medicineCount;
    }

    private void fillSpinnerWithStrings(Spinner spinner, String prefix, List<String> strings) {
        List<String> spinnerStrings = new ArrayList<>();

        spinnerStrings.add(prefix);

        if (strings != null)
            spinnerStrings.addAll(strings);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                (String[]) spinnerStrings.toArray(new String[spinnerStrings.size()]));
        spinner.setAdapter(spinnerAdapter);
    }

    private List<String> getStringsFromMedicineType() {
        List<String> medicineTypeStrings = new ArrayList<>();

        for (EMedicineType i : EMedicineType.values()) {
            medicineTypeStrings.add(i.getName());
        }

        return medicineTypeStrings;
    }
}
