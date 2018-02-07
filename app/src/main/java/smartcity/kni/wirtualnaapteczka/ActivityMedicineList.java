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
        dummyMedicines.add(new DummyMedicine("Apap", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Ibuprom", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Nospa", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Nurofen", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Laremid", "Przeciw biegunce"));
        dummyMedicines.add(new DummyMedicine("Stoperan", "Przeciw zaparciom"));
        dummyMedicines.add(new DummyMedicine("Acalut", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Ketonal", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Rutinoscorbin", "Witaminy"));
        dummyMedicines.add(new DummyMedicine("Nimesil", "Silny przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Ketoprom", "Przeciwbólowy"));
        dummyMedicines.add(new DummyMedicine("Ibuprofen", "Przeciwgorączkowy"));
        // --- DUMMY DATA!!! ---

        ListView medicineListView = (ListView) findViewById(R.id.Medicine_ListView);
        mMedicineListAdapter = new MedicineListAdapter(this, dummyMedicines);
        medicineListView.setAdapter(mMedicineListAdapter);
    }
}
