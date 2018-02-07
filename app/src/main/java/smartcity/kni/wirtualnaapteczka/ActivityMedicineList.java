package smartcity.kni.wirtualnaapteczka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.adapters.DummyMedicine;
import smartcity.kni.wirtualnaapteczka.adapters.MedicineListAdapter;

public class ActivityMedicineList extends AppCompatActivity {

    MedicineListAdapter mMedicineListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        setTitle(R.string.title_activity_medicine_list);

        // --- DUMMY DATA!!! ---
        ArrayList<DummyMedicine> dummyMedicines = new ArrayList<>();
        dummyMedicines.add(new DummyMedicine("Apap"));
        dummyMedicines.add(new DummyMedicine("Ibuprom"));
        dummyMedicines.add(new DummyMedicine("Nospa"));
        dummyMedicines.add(new DummyMedicine("Nurofen"));
        dummyMedicines.add(new DummyMedicine("Laremid"));
        dummyMedicines.add(new DummyMedicine("Stoperan"));
        dummyMedicines.add(new DummyMedicine("Acalut"));
        dummyMedicines.add(new DummyMedicine("Ketonal"));
        dummyMedicines.add(new DummyMedicine("Rutinoscorbin"));
        dummyMedicines.add(new DummyMedicine("Nimesil"));
        dummyMedicines.add(new DummyMedicine("Ketoprom"));
        dummyMedicines.add(new DummyMedicine("Ibuprofen"));
        // --- DUMMY DATA!!! ---

        ListView medicineListView = (ListView) findViewById(R.id.Medicine_ListView);
        mMedicineListAdapter = new MedicineListAdapter(this,dummyMedicines);
        medicineListView.setAdapter(mMedicineListAdapter);
    }
}
