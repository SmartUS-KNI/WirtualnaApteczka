package smartcity.kni.wirtualnaapteczka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.adapters.MedicineListAdapter;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

public class ActivityMedicineList extends AppCompatActivity {

    MedicineListAdapter mMedicineListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        setTitle(R.string.title_activity_medicine_list);

        SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
        // ---!!!--- ADD THIS LINE TO ADD MEDICINE TO DATABASE FOR TESTING LIST ---!!!---
        // ---!!!--- REMEMBER TO RUN IT ONLY ONCE! ---!!!---
        //sqLiteDatabaseHelper.insertMedicine(new smartcity.kni.wirtualnaapteczka.Medicine(null, "Ibuprom", "Lek przeciwbólowy mający działanie szczególnie na bóle głowy.", null, null, null));
        List<smartcity.kni.wirtualnaapteczka.Medicine> medicinesList = sqLiteDatabaseHelper.getAllMedicine();


        ListView medicineListView = (ListView) findViewById(R.id.Medicine_ListView);
        mMedicineListAdapter = new MedicineListAdapter(this, medicinesList);
        medicineListView.setAdapter(mMedicineListAdapter);
    }
}
