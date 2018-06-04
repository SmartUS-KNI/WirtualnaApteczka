package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartcity.kni.wirtualnaapteczka.Medicine_Count;
import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.enums.EMedicineType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.filters.Config;
import smartcity.kni.wirtualnaapteczka.filters.DecimalDigitsInputFilter;
import smartcity.kni.wirtualnaapteczka.filters.SpecialCharactersInputFilter;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.layout.helpers.SpinnerHelper;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;
import smartcity.kni.wirtualnaapteczka.filters.DecimalDigitsInputFilter;

public class MedicineFormActivity extends AppCompatActivity {

    final SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
    boolean skipFillingMedicineTypeUnitSpinnerFlag = false;
    boolean modifyModeFlag = false;
    Medicine currentMed = null;

    @Override
    protected void onDestroy() {
        if (!modifyModeFlag && currentMed.getName() == null)
            deleteMedicineFromDatabase(currentMed.getId());
        super.onDestroy();
    }

    private Medicine generateTemporaryMedicine() {
        return new Medicine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_medicine_form);
        initBarcodeFromCameraImplementation();

        /**
         * @author KozMeeN
         * View will be complete with information from sent Medicine, if we will choose edit option, if we will choose create new medicine view will has
         * default values.
         */

        Log.v("MedicineForm: #1 Leki", sqLiteDatabaseHelper.getAllMedicine().toString());

        if (getIntent().hasExtra("Id")) {
            currentMed = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));
            Log.v("MedicineForm: ", "Modifying Medicine id = " + currentMed.getId());
            modifyModeFlag = true;
            setContent(currentMed);
        } else {
            currentMed = generateTemporaryMedicine();
            long currentMedId = addMedicineToDatabase(currentMed);
            Log.v("MedicineForm: ", "New Medicine id = " + currentMedId);
        }

        //SPRAWDZENIE LOGCATEM -- DEBUG ONLY
        Log.v("MedicineForm: #2 Leki", sqLiteDatabaseHelper.getAllMedicine().toString());

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
        countCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                countingContainer.setVisibility(View.VISIBLE);
            else
                countingContainer.setVisibility(View.GONE);
        });
        dosageCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                dosageContainer.setVisibility(View.VISIBLE);
            else
                dosageContainer.setVisibility(View.GONE);
        });

        SpinnerHelper.fillSpinnerWithStrings(medicineType, getString(R.string.medicine_type), this.getStringsFromMedicineType());

        if (modifyModeFlag) {
            countCheckBox.setChecked(true);
            ((TextView) findViewById(R.id.add_Medicine_From_New_Medicine_TextView)).setText(R.string.modify_medicine); //Ustawienie tytułu
            if (currentMed.getMedicine_Count() != null) {
                medicineType.setSelection((int) currentMed.getMedicine_Count().getMedicineType() + 1);
                SpinnerHelper.fillSpinnerWithStrings(medicineTypeUnit, getString(R.string.medicine_type_unit), EMedicineType.values()[medicineType.getSelectedItemPosition() - 1].getUnits());
                medicineTypeUnit.setSelection(currentMed.getMedicine_Count().getMedicineTypeUnit() + 1);
            }
            skipFillingMedicineTypeUnitSpinnerFlag = true;
        }


        medicineType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (skipFillingMedicineTypeUnitSpinnerFlag) {
                    skipFillingMedicineTypeUnitSpinnerFlag = false;
                    return;
                }

                if (position > 0)
                    SpinnerHelper.fillSpinnerWithStrings(medicineTypeUnit, getString(R.string.medicine_type_unit), EMedicineType.values()[position - 1].getUnits());
                else
                    SpinnerHelper.fillSpinnerWithStrings(medicineTypeUnit, getString(R.string.medicine_type_unit), null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //NOTHING TO DO
            }
        });

        applyValidationToContent();

        addDosage.setOnClickListener(view -> {
            if (currentMed.getId() == null) {
                Intent intent = new Intent(MedicineFormActivity.this, AddNewDoseActivity.class);
                startActivity(intent);
            }
        });

        submitFormButton.setOnClickListener(v -> {
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

                if (getIntent().hasExtra("Id")) {
                    Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));
                    updateMedicineInDatabase(generateMedicineFromContent(medicine, content));
                } else {
                    updateMedicineInDatabase(generateMedicineFromContent(currentMed, content));
                }

                if (modifyModeFlag) {
                    Toast.makeText(getApplicationContext(), R.string.modify_medicine_success, Toast.LENGTH_SHORT).show();
                } else {
                    if (!currentMed.getName().equals("")) {
                        Toast.makeText(getApplicationContext(), R.string.add_medicine_success, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.add_medicine_failure, Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            } else
                Toast.makeText(getApplicationContext(), R.string.form_validation_failure, Toast.LENGTH_SHORT).show();
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

    private Medicine generateMedicineFromContent(Medicine medicine, LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();

        medicine.setName((String) contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        medicine.setDescription((String) contentMap.get(R.id.description_Of_New_Medicine_EditText));
        medicine.setEAN((String) contentMap.get(R.id.barcode_From_New_Medicine_EditText));
        medicine.setMedicine_Count(this.generateMedicineCountFromContent(content));

        return medicine;
    }

    private Medicine_Count generateMedicineCountFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Medicine_Count medicineCount = new Medicine_Count();

        EMedicineType selectedType = null;

        if (!((String) contentMap.get(R.id.count)).isEmpty() && Double.compare(Double.parseDouble((String) contentMap.get(R.id.count)), new Double(0)) > 0) {
            if ((int) contentMap.get(R.id.medicine_type) > 0)
                selectedType = EMedicineType.values()[(int) contentMap.get(R.id.medicine_type) - 1];

            medicineCount.setCount(Double.parseDouble((String) contentMap.get(R.id.count)));

            if (selectedType != null) {
                medicineCount.setMedicineType(selectedType.getId());
                medicineCount.setMedicineTypeUnit((int) contentMap.get(R.id.medicine_type_unit) - 1);
            }

            SQLiteDatabaseHelper.getInstance().insertMedicine_Count(medicineCount);
        }

        return medicineCount;
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
        EditText medicineQuantityEditText = (EditText) findViewById(R.id.count);

        medicineNameEditText.setText(medicine.getName());
        medicineBarcodeEditText.setText(medicine.getEAN());
        medicineDescriptionEditText.setText(medicine.getDescription());
        if (medicine.getMedicine_Count() != null)
            medicineQuantityEditText.setText(medicine.getMedicine_Count().getCount().toString());
        else
            return;
    }

    private long addMedicineToDatabase(Medicine medicine) {

        SQLiteDatabaseHelper instance = SQLiteDatabaseHelper.getInstance();
        long id = medicine.getId() == null ? -1 : medicine.getId();
        if (instance.getMedicineById(id) == null)
            return instance.insertMedicine(medicine);
        else {
            updateMedicineInDatabase(medicine);
            return medicine.getId();
        }
    }

    /**
     * @param medicine object which we want to update in database.
     * @author KozMeeN
     * method update selected medicine in badabase.
     */
    private void updateMedicineInDatabase(Medicine medicine) {
        SQLiteDatabaseHelper.getInstance().updateMedicine(medicine);
    }

    private void deleteMedicineFromDatabase(Long id) {
        SQLiteDatabaseHelper.getInstance().deleteMedicineById(id);
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

    private void initBarcodeFromCameraImplementation() {

        ImageButton imageButtonBarcodeFromCamera = (ImageButton) this.findViewById(R.id.barcode_From_Camera);

        imageButtonBarcodeFromCamera.setOnClickListener(v -> {
            IntentIntegrator integrator = new IntentIntegrator(MedicineFormActivity.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
            integrator.setPrompt(getString(R.string.prompt_for_barcode_scanner));
            integrator.setCameraId(0);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, getString(R.string.cancelled_scanner_barcode), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.correct_scanner_barcode), Toast.LENGTH_LONG).show();
                EditText barcode = (EditText) findViewById(R.id.barcode_From_New_Medicine_EditText);
                barcode.setText(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
