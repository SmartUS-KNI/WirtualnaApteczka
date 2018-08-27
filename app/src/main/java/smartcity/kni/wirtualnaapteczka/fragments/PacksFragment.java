package smartcity.kni.wirtualnaapteczka.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import smartcity.kni.wirtualnaapteczka.AddNewPackActivity;
import smartcity.kni.wirtualnaapteczka.MedicineFormActivity;
import smartcity.kni.wirtualnaapteczka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacksFragment extends Fragment {

    View fragmentView;
    ListView packsListView;
    FloatingActionButton addNewPackButton;

    public PacksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_packs, container, false);

        initViews();

        return fragmentView;
    }

    private void initViews() {
        packsListView = (ListView) fragmentView.findViewById(R.id.packs_list);
        addNewPackButton = (FloatingActionButton) fragmentView.findViewById(R.id.add_new_pack_button);

        addNewPackButton.setOnClickListener((view) -> {
            startActivity(new Intent(MedicineFormActivity.getContext(), AddNewPackActivity.class));
        });
    }
}
