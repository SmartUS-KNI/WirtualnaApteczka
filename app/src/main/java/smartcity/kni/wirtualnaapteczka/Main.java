package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.layout.content.ViewManager;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        sqLiteDatabaseHelper.openConnection(this, "medicinesDatabase");

        try {
            ViewManager.getInstance().setContentTypeConverters();
        } catch (MissingConverterException e) {
            e.printStackTrace();
        }

        Button addNewMedicineButton = (Button) findViewById(R.id.add_Medicine_Main_Button);
        Button openMedicineListButton = (Button) findViewById(R.id.medicine_List_Main_Button);
        Button appExit = (Button) findViewById(R.id.end_Program_Main_Button);

        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, MedicineFormActivity.class));
            }
        });

        openMedicineListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, ActivityMedicineList.class));
            }
        });

        appExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

