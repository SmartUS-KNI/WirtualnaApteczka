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


    /**
     * edit KozMeeN
     * I place all instruction in separate method, thanks what we can open it in onCreate method, and onResume method.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        updateView();
    }

    /**
     * @author KozMeeN
     * I added this method becouse when we edit Medicine, and back to this Acitivity (by return button) list did not display the changes we have made.
     */
    protected void onResume() {
        super.onResume();
        updateView();

    }


    /**
     * @author KozMeeN
     *
     * Function open new Activity
     * @see MedicineInfoActivity
     * where we can see informations about chosen medicine.
     *
     * We will sentd id of medicine in extras.
     *
     * @param medicine object, which will be edit.
     */
    public void onClickListenerToMedicineList(Medicine medicine){
                Long medicineId = medicine.getId();
                Intent intent = new Intent(ActivityMedicineList.this,MedicineInfoActivity.class);
                intent.putExtra("Id",medicineId);
                startActivity(intent);
            }

    /**
     * @author KozMeeN
     * method cerete a list of medicine and  make possible start MedicineInfoActivity.
     */
    private void updateView(){
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


}
