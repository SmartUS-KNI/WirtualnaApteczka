package smartcity.kni.wirtualnaapteczka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.List;
import smartcity.kni.wirtualnaapteczka.adapters.MedicineListAdapter;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;
import smartcity.kni.wirtualnaapteczka.Medicine;

public class ActivityMedicineList extends AppCompatActivity {

    MedicineListAdapter mMedicineListAdapter;
    SearchView searchView;
    List<Medicine> medicinesList;
    ListView medicineListView;


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
        medicinesList = sqLiteDatabaseHelper.getAllMedicine();

        medicineListView = (ListView) findViewById(R.id.Medicine_ListView);
        mMedicineListAdapter = new MedicineListAdapter(this, medicinesList);
        medicineListView.setAdapter(mMedicineListAdapter);


        medicineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickListenerToMedicineList(medicinesList.get(i));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMedicineListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mMedicineListAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
