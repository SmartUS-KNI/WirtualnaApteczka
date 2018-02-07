package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;

import smartcity.kni.wirtualnaapteczka.enums.ELayoutContentType;
import smartcity.kni.wirtualnaapteczka.exceptions.MissingConverterException;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContent;
import smartcity.kni.wirtualnaapteczka.layout.content.LayoutContentConfig;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_medicine_info);
        //setContentView(R.layout.activity_medicine_list);
        //setContentView(R.layout.activity_new_medicine_form);

        Button openAddMedicineFormButton = (Button) findViewById(R.id.add_Medicine_Main_Button);
        Button openMedicineListButton = (Button) findViewById(R.id.medicine_List_Main_Button);
        Button closeAppButton = (Button) findViewById(R.id.end_Program_Main_Button);

        openAddMedicineFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO Add functionality to Button opening Add Medicine Form
            }
        });

        openMedicineListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, ActivityMedicineList.class));
            }
        });

        closeAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
