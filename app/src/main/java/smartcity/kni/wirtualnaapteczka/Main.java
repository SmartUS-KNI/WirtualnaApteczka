package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_medicine_info);
        //setContentView(R.layout.activity_medicine_list);
        //setContentView(R.layout.activity_new_medicine_form);

        Button addNewMedicineButton = (Button) findViewById(R.id.add_Medicine_Main_Button);

        addNewMedicineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, NewMedicineFormActivity.class));
            }
        });

    }


}
