package smartcity.kni.wirtualnaapteczka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Map;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class NewMedicineFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_new_medicine_form);

        //TODO: Adding submit button with onClick listener, which gets content, sets to Medicine object and sends to database
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
}
