package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import smartcity.kni.wirtualnaapteczka.Medicine;
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
        final List<Medicine> medicinesList = sqLiteDatabaseHelper.getAllMedicine();

        ListView medicineListView = (ListView) findViewById(R.id.Medicine_ListView);
        mMedicineListAdapter = new MedicineListAdapter(this, medicinesList);
        medicineListView.setAdapter(mMedicineListAdapter);


        /**
         * @author KozMeeN
         * Medicine list services
         */
        medicineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickListenerToMedicineList(medicinesList.get(i));
            }
        });

    }

    /**
     * @author KozMeeN
     *
     * Function open new Activity
     * @see ShowMedicineInfoActivity
     * where we can see informations about chosen medicine.
     *
     * We will sentd id of medicine in extras.
     *
     * @param medicine object, which will be edit.
     */
    public void onClickListenerToMedicineList(Medicine medicine){

                Long medicineId = medicine.getId();
                Intent intent = new Intent(ActivityMedicineList.this,ShowMedicineInfoActivity.class);
                intent.putExtra("Id",medicineId);
                startActivity(intent);
            }


}
