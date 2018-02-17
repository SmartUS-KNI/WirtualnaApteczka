package smartcity.kni.wirtualnaapteczka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Map;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
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

        Button submitFormButton = (Button) findViewById(R.id.submit_From_New_Medicine_Button);

        submitFormButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View layout = findViewById(R.id.root_From_New_Medicine_Layout);

                LayoutContentConfig contentConfig = new LayoutContentConfig();
                contentConfig.addLayoutContentConfigParam(ELayoutContentType.LAYOUT_CONTENT_TYPE_EDITTEXT);

                LayoutContent content = null;
                try {
                    content = ViewManager.getInstance().getContent(layout, contentConfig);
                } catch (MissingConverterException e) {
                    e.printStackTrace();
                    toastForAddMedicineToDatabase("lek nie został zapisany");
                }

                addMedicineToDatabase(generateMedicineFromContent(content));
                toastForAddMedicineToDatabase("lek został poprawnie zapisany");

                finish();
            }
        });
    }

    private Medicine generateMedicineFromContent(LayoutContent content) {
        Map<Integer, Object> contentMap = content.getContentMap();
        Medicine medicine = new Medicine();

        medicine.setName((String)contentMap.get(R.id.name_Of_Medicine_From_New_Medicine_EditText));
        medicine.setDescription((String)contentMap.get(R.id.description_Of_New_Medicine_EditText));
        medicine.setEAN((String)contentMap.get(R.id.barcode_From_New_Medicine_EditText));

        return medicine;
    }

    private void addMedicineToDatabase(Medicine medicine) {
        SQLiteDatabaseHelper.getInstance().insertMedicine(medicine);
    }

    private void toastForAddMedicineToDatabase(String text){
//        Context context = getApplicationContext();
//        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

}
