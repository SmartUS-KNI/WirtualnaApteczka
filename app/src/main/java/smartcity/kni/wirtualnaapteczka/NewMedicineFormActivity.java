package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.Medicine_Count;
import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.enums.EMedicineType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.filters.Config;
import smartcity.kni.wirtualnaapteczka.filters.SpecialCharactersInputFilter;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;
import smartcity.kni.wirtualnaapteczka.filters.DecimalDigitsInputFilter;

public class NewMedicineFormActivity extends AppCompatActivity {

    Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_medicine_form);

        /**
         * @author KozMeeN
         * View will be complete with information from sent Medicine, if we will choose edit option, if we will choose create new medicine view will has
         * default values.
         */

        final SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();

        if (getIntent().hasExtra("Id")) {
            medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));
            setContent(medicine);
        }else{
            medicine = new Medicine();
        }

        final LinearLayout countingContainer = (LinearLayout) findViewById(R.id.counting_container);
        /*** Container to add dosage of medicine.*/
        final LinearLayout dosageContainer = (LinearLayout) findViewById(R.id.dosage_container);
        CheckBox countCheckBox = (CheckBox) findViewById(R.id.check_counting);
        CheckBox dosageCheckBox = (CheckBox) findViewById(R.id.dosageCheckBox);
        Spinner medicineType = (Spinner) findViewById(R.id.medicine_type);
        final Spinner medicineTypeUnit = (Spinner) findViewById(R.id.medicine_type_unit);
        Button submitFormButton = (Button) findViewById(R.id.submit_From_New_Medicine_Button);
        Button addDosage = (Button) findViewById(R.id.addDosageButton);

        countingContainer.setVisibility(View.GONE);

        /***
         * @author KozMeeN
         * Dosage Container will be hidden on start activity, will show up when we click checkBox.*/
        dosageContainer.setVisibility(View.GONE);
        countCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    countingContainer.setVisibility(View.VISIBLE);
                else
                    countingContainer.setVisibility(View.GONE);
            }
        });

        dosageCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    dosageContainer.setVisibility(View.VISIBLE);
                else
                    dosageContainer.setVisibility(View.GONE);
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

        applyValidationToContent();

        addDosage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewMedicineFormActivity.this,AddNewDosageActivity.class);
                intent.putExtra("id",medicine.getId());
                startActivity(intent);

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

                if (isFormValid(content)) {
                    long newMedicineId = -1;


                    /**
                     * @author KozMeeN
                     * when we edit medicine, we will work for exist medicine.
                     * finally we update this medicine in database.
                     *
                     * when we create new medicine we create a new medicine so we dont have to sent this object in method.
                     */

                    if (getIntent().hasExtra("Id")) {
                        Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));
                        updateMedicineInDatabase(generateMedicineFromContent(content));
                    } else {
                        newMedicineId = addMedicineToDatabase(generateMedicineFromContent(content));
                    }
                    if (newMedicineId != -1) {
                        Toast.makeText(getApplicationContext(), R.string.add_medicine_success, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.add_medicine_failure, Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), R.string.form_validation_failure, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Medicine_Count generateMedicineCountFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Medicine_Count medicineCount = new Medicine_Count();

        EMedicineType selectedType = EMedicineType.values()[(int) contentMap.get(R.id.medicine_type) - 1];

        medicineCount.setCount(Double.parseDouble((String) contentMap.get(R.id.count)));
        medicineCount.setMedicineType(selectedType.getId());
        medicineCount.setMedicineTypeUnit((int) contentMap.get(R.id.medicine_type_unit) - 1);

        SQLiteDatabaseHelper.getInstance().insertMedicine_Count(medicineCount);

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

    /**
     * @param medicine medicine which values will be set in the view.
     * @author KozMeeN
     * <p>
     * method set values od the object in this view.
     */
    private void setContent(Medicine medicine) {
        EditText medicineNameEditText = (EditText) findViewById(R.id.name_Of_Medicine_From_New_Medicine_EditText);
        EditText medicineDescriptionEditText = (EditText) findViewById(R.id.description_Of_New_Medicine_EditText);
        EditText medicineBarcodeEditText = (EditText) findViewById(R.id.barcode_From_New_Medicine_EditText);

        medicineNameEditText.setText(medicine.getName());
        medicineDescriptionEditText.setText(medicine.getDescription());
        medicineBarcodeEditText.setText(medicine.getEAN());
    }

    private Medicine generateMedicineFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();

        medicine.setName((String) contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        medicine.setDescription((String) contentMap.get(R.id.description_Of_New_Medicine_EditText));
        medicine.setEAN((String) contentMap.get(R.id.barcode_From_New_Medicine_EditText));
        medicine.setMedicine_Count(this.generateMedicineCountFromContent(content));

        return medicine;
    }

    private long addMedicineToDatabase(Medicine medicine) {
        return SQLiteDatabaseHelper.getInstance().insertMedicine(medicine);
    }

    /**
     * @param medicine object which we want to update in database.
     * @author KozMeeN
     * method update selected medicine in badabase.
     */
    private void updateMedicineInDatabase(Medicine medicine) {
        SQLiteDatabaseHelper.getInstance().updateMedicine(medicine);
    }

    private void applyValidationToContent() {
        ((EditText) findViewById(R.id.name_Of_Medicine_From_New_Medicine_EditText)).setFilters(
                new InputFilter[]{
                        new InputFilter.LengthFilter(Config.MAX_LENGTH),
                        new SpecialCharactersInputFilter()
                }
        );
        ((EditText) findViewById(R.id.description_Of_New_Medicine_EditText)).setFilters(
                new InputFilter[]{
                        new InputFilter.LengthFilter(Config.MAX_LENGTH),
                        new SpecialCharactersInputFilter()
                }
        );
        ((EditText) findViewById(R.id.count)).setFilters(
                new InputFilter[]{
                        new DecimalDigitsInputFilter()
                }
        );
        ((EditText) findViewById(R.id.barcode_From_New_Medicine_EditText)).setFilters(
                new InputFilter[]{
                        new InputFilter.LengthFilter(13)            //EAN-13
                }
        );

    }

    private boolean isFormValid(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();

        if (contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText).toString().isEmpty())
            return false;

        return true;
    }
}
