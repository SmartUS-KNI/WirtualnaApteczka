package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import smartcity.kni.wirtualnaapteczka.listeners.AddMedicineActivity;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_medicine_info);
        //setContentView(R.layout.activity_medicine_list);
        //setContentView(R.layout.activity_new_medicine_form);
    }

    public void OpenAddMedicineActivity(View view){
        Intent intent = new Intent(this,AddMedicineActivity.class);
        startActivity(intent);
    }


}
