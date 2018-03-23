package smartcity.kni.wirtualnaapteczka;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.enums.EMedicineType;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

/**
 * Created by gosia on 26.02.2018.
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
        Medicine medicine = sqLiteDatabaseHelper.getMedicineById(getIntent().getLongExtra("Id", 0));

        TextView nameOfMedicine = (TextView) findViewById(R.id.name_Of_Medicine_Info_TextView);
        nameOfMedicine.setText(medicine.getName());

        //ilość
        TextView quantityOfMedicine = (TextView) findViewById(R.id.quantity_Of_Medicine_Info_TextView2);
        quantityOfMedicine.setText(medicine.getMedicine_Count().getCount().toString() +
                " " +
                EMedicineType.getMedicineTypeById(medicine.getMedicine_Count()
                        .getMedicineType())
                        .getUnits()
                        .get(medicine.getMedicine_Count()
                                .getMedicineTypeUnit())
                                .toString());               //Nie pytać

        //Rodzaj
        TextView typeOfMedicine = (TextView) findViewById(R.id.type_Of_Medicine_Info_TextView);
        typeOfMedicine.setText(EMedicineType.getMedicineTypeById(medicine.getMedicine_Count().getMedicineType()).getName());       

        TextView descriptionOfMedicine = (TextView) findViewById(R.id.decsription_From_Medicin_Info_TextView);
        descriptionOfMedicine.setText(medicine.getDescription());

        TextView barcodeOfMedicine = (TextView) findViewById(R.id.barcode_From_Medicine_Info_TextView);
        barcodeOfMedicine.setText(medicine.getEAN());


        Button updateButton = (Button) findViewById(R.id.modify_Medicine_Info_Button);
        Button deleteButton = (Button) findViewById(R.id.delete_Medicine_Info_Button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicineInfoActivity.this, MedicineFormActivity.class);

                Long medicineId = getIntent().getLongExtra("Id", 0);
                intent.putExtra("Id", medicineId);
                //
                intent.putExtra("ModifyMode", true);
                //
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MedicineInfoActivity.this);
                builder.setTitle(R.string.delete_title);
                builder.setMessage(R.string.delete_confirm_question);
                builder.setPositiveButton(R.string.answer_yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // here in case of positive ansver program is closeing
                        Long idMedicine = MedicineInfoActivity.this.getIntent().getLongExtra("Id", 0);

                        SQLiteDatabaseHelper.getInstance().deleteMedicineById(idMedicine);

                        Toast.makeText(MedicineInfoActivity.this, R.string.info_after_delete_medicine, Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });

                builder.setNegativeButton(R.string.answer_no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
