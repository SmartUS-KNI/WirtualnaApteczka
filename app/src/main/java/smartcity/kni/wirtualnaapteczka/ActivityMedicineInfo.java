package smartcity.kni.wirtualnaapteczka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

/**
 * Created by gosia on 26.02.2018.
 */

public class ActivityMedicineInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_medicine_info);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        smartcity.kni.wirtualnaapteczka.Medicine medicie = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        TextView nameOfMedicine = (TextView) findViewById(R.id.name_Of_Medicine_Info_TextView);
        nameOfMedicine.setText(medicie.getName());

        TextView descriptionOfMedicine = (TextView) findViewById(R.id.decsription_From_Medicin_Info_TextView);
        descriptionOfMedicine.setText(medicie.getDescription());

        TextView barcodeOfMedicine = (TextView) findViewById(R.id.barcode_From_Medicine_Info_TextView);
        barcodeOfMedicine.setText(medicie.getEAN());
    }
}
