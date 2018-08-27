package smartcity.kni.wirtualnaapteczka.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineBaseInfoFragment extends Fragment {

    SQLiteDatabaseHelper sqLiteDatabaseHelper;

    View fragmentView;
    EditText medicineNameEditText;
    Spinner medicineTypeSpinner;
    Spinner medicineFormSpinner;
    AutoCompleteTextView medicineResponsibleSubjectAcTextView;
    EditText medicineDescriptionEditText;


    public MedicineBaseInfoFragment() {
        // Required empty public constructor
    }

    public static MedicineBaseInfoFragment newInstance(int page, String title) {
        MedicineBaseInfoFragment medicineBaseInfoFragment = new MedicineBaseInfoFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        medicineBaseInfoFragment.setArguments(args);
        return medicineBaseInfoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sqLiteDatabaseHelper = SQLiteDatabaseHelper.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_medicine_base_info, container, false);

        initViews();

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initViews() {
        medicineNameEditText = (EditText) fragmentView.findViewById(R.id.name_Of_Medicine_From_New_Medicine_EditText);
        medicineTypeSpinner = (Spinner) fragmentView.findViewById(R.id.medicine_type_spinner);
        medicineFormSpinner = (Spinner) fragmentView.findViewById(R.id.medicine_form_spinner);
        medicineResponsibleSubjectAcTextView = (AutoCompleteTextView) fragmentView.findViewById(R.id.medicine_responsible_subject_actextview);
        medicineDescriptionEditText = (EditText) fragmentView.findViewById(R.id.description_Of_New_Medicine_EditText);
    }
}
