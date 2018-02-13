package smartcity.kni.wirtualnaapteczka.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.net.database.SQLiteDatabaseHelper;

/**
 * Created by Radek on 07-Feb-18.
 */

public class MedicineListAdapter extends ArrayAdapter<Medicine> {

    public MedicineListAdapter(Activity context, List<Medicine> medicines){
        super(context, 0, medicines);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.medicine_list_view_item, null);
        }

        Medicine medicine = getItem(position);

        TextView medicineNameTV = (TextView) listItemView.findViewById(R.id.medicine_name_text_view);
        medicineNameTV.setText(medicine.getName());

        TextView medicineTagTV = (TextView) listItemView.findViewById(R.id.medicine_tag_text_view);
        medicineTagTV.setText(medicine.getDescription());

        return listItemView;

    }
}
