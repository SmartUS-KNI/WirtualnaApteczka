package smartcity.kni.wirtualnaapteczka;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.List;
import smartcity.kni.wirtualnaapteczka.Medicine;

import smartcity.kni.wirtualnaapteczka.Popups.PopupSort;
import smartcity.kni.wirtualnaapteczka.adapters.MedicineListAdapter;
import smartcity.kni.wirtualnaapteczka.comparators.AlphabeticalComparator;
import smartcity.kni.wirtualnaapteczka.comparators.Comparator;
import smartcity.kni.wirtualnaapteczka.enums.ESort;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

import static smartcity.kni.wirtualnaapteczka.enums.ESort.ALPHABETIC;

public class ActivityMedicineList extends AppCompatActivity {

    MedicineListAdapter mMedicineListAdapter;
    SearchView searchView;
    List<Medicine> medicinesList = null;
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
        Button sortButton = (Button) findViewById(R.id.sortButton);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ActivityMedicineList.this, PopupSort.class), PopupSort.POPUP_SORT);
            }
        });
    }

    /**
     * @author KozMeeN 1.06.2018
     *
     * metods serve method startActivityForResult. Here we will implements  all sort operations.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == PopupSort.POPUP_SORT) {
                ESort eSort = (ESort) data.getSerializableExtra("result");
                switch (eSort){
                    case ALPHABETIC:{
                        medicinesList = sort(new AlphabeticalComparator());
                        break;
                    }
                }
            updateView();
        }
    }

    /**
     * @author KozMeeN
     * I added this method becouse when we edit Medicine, and back to this Acitivity (by return button) list did not display the changes we have made.
     */
    protected void onResume() {
        super.onResume();
        if(medicinesList == null){
            SQLiteDatabaseHelper sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
            medicinesList = sqLiteDatabaseHelper.getAllMedicine();
        }
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
    public void onClickListenerToMedicineList(smartcity.kni.wirtualnaapteczka.Medicine medicine){
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

    /**
     * @author KozMeeN 31.05.2018
     *
     * method sort medicinesList in case of comperator type
     *
     * @param comparator class implements of Comparator, where must be implemented sort method.
     * @return sorted medicinesList.
     */
    private List<Medicine> sort(Comparator comparator){
        return comparator.compare(medicinesList);
    }
}
