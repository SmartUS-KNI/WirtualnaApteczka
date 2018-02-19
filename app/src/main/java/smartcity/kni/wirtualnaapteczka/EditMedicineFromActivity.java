package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

import smartcity.kni.wirtualnaapteczka.Medicine;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;


/**
 * @author KozMeeN
 * In this activity we can update chosen medicine.
 *
 *
 */
public class EditMedicineFromActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_medicine_form);

        EditText medicineNameEditText = (EditText) findViewById(R.id.name_Of_Medicine_From_New_Medicine_EditText);
        EditText medicineDescriptionEditText = (EditText) findViewById(R.id.description_Of_New_Medicine_EditText);
        EditText medicineBarcodeEditText = (EditText) findViewById(R.id.barcode_From_New_Medicine_EditText);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        /**
        *We need get id of medicine to update, with extra in intent.
         */
        final Medicine medicie = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        medicineNameEditText.setText(medicie.getName());
        medicineDescriptionEditText.setText(medicie.getDescription());
        medicineBarcodeEditText.setText(medicie.getEAN());

        Button submitFormButton = (Button) findViewById(R.id.submit_From_New_Medicine_Button);

        submitFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View layout = findViewById(R.id.root_From_New_Medicine_Layout);

                LayoutContentConfig contentConfig = new LayoutContentConfig();
                contentConfig.addLayoutContentConfigParam(ELayoutContentType.LAYOUT_CONTENT_TYPE_EDITTEXT);

                LayoutContent content = null;
                try {
                    content = ViewManager.getInstance().getContent(layout, contentConfig);
                } catch (MissingConverterException e) {
                    e.printStackTrace();
                }

                addMedicineToDatabase(generateMedicineFromContent(medicie, content));

                finish();
            }
        });
    }
    private Medicine generateMedicineFromContent(Medicine medicine, LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();

        medicine.setName((String)contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        medicine.setDescription((String)contentMap.get(R.id.description_Of_New_Medicine_EditText));
        medicine.setEAN((String)contentMap.get(R.id.barcode_From_New_Medicine_EditText));

        return medicine;
    }

    private void addMedicineToDatabase(Medicine medicine) {
        SQLiteDatabaseHelper.getInstance().updateMedicine(medicine);
    }

}
