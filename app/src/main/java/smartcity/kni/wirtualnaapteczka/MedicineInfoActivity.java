package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import smartcity.kni.wirtualnaapteczka.Medicine;



import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

/**@author KozMeeN
 *
 * From this Activity, we can edit or delete the medicine.
 *
 *
 */
public class MedicineInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_medicine_info);

        /**
         * @author KozMeeN it wasn't my task, byt I did it becouse it was helpful in my task.
         *
        */
        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        Medicine medicie = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id",0));

        /**
         * EditText nameOfMedicine = (EditText) findViewById(R.id.name_Of_Medicine_Info_EditText);
        nameOfMedicine.setText(medicie.getName());

        TextView descriptionOfMedicine = (TextView) findViewById(R.id.decsription_From_Medicin_Info_TextView);
        descriptionOfMedicine.setText(medicie.getDescription());

        EditText barcodeOfMedicine = (EditText) findViewById(R.id.barcode_From_Medicine_Info_EditText);
        barcodeOfMedicine.setText(medicie.getEAN());
        */

        Button updateButton = (Button) findViewById(R.id.modify_Medicine_Info_Button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MedicineInfoActivity.this,NewMedicineFormActivity.class);

                Long medicineId = getIntent().getLongExtra("Id", 0);
                intent.putExtra("Id",medicineId);

                startActivity(intent);
            }
        });
    }
}


