package smartcity.kni.wirtualnaapteczka;

import android.content.BroadcastReceiver;
import android.content.Context;
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

        Intent intent = new Intent(this, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        }.getClass());


        Button addNewMedicineButton = (Button) findViewById(R.id.add_Medicine_Main_Button);
        Button openMedicineListButton = (Button) findViewById(R.id.medicine_List_Main_Button);
        Button appExit = (Button) findViewById(R.id.end_Program_Main_Button);

        addNewMedicineButton.setOnClickListener(v -> startActivity(new Intent(Main.this, MedicineFormActivity.class)));

        openMedicineListButton.setOnClickListener(v -> startActivity(new Intent(Main.this, ActivityMedicineList.class)));

        appExit.setOnClickListener(v -> finish());
    }
}

