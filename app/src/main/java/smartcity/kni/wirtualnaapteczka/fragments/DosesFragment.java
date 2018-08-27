package smartcity.kni.wirtualnaapteczka.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import smartcity.kni.wirtualnaapteczka.AddNewDoseActivity;
import smartcity.kni.wirtualnaapteczka.MedicineFormActivity;
import smartcity.kni.wirtualnaapteczka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosesFragment extends Fragment {

    View fragmentView;

    ListView dosesListView;
    FloatingActionButton addNewDoseButton;

    public DosesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_doses, container, false);

        initViews();

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initViews() {
        dosesListView = (ListView) fragmentView.findViewById(R.id.doses_list);
        addNewDoseButton = (FloatingActionButton) fragmentView.findViewById(R.id.add_new_dose_button);

        addNewDoseButton.setOnClickListener((view) -> {
            startActivity(new Intent(MedicineFormActivity.getContext(), AddNewDoseActivity.class));
        });
    }
}
