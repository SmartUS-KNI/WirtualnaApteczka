package smartcity.kni.wirtualnaapteczka;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class MedicineInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button deleteButton = (Button) findViewById(R.id.delete_Medicine_Info_Button);

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
