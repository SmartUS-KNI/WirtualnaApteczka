package smartcity.kni.wirtualnaapteczka.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.Medicine;
import smartcity.kni.wirtualnaapteczka.R;

/**
 * Created by Radek on 07-Feb-18.
 */

public class MedicineListAdapter extends ArrayAdapter<Medicine> implements Filterable {

    NameFilter nameFilter;
    private List<Medicine> medicines;
    private List<Medicine> mStringFilterList;

    public MedicineListAdapter(Activity context, List<Medicine> medicines){
        super(context, 0, medicines);
        mStringFilterList = medicines;
        this.medicines = medicines;
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

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Nullable
    @Override
    public Medicine getItem(int position) {
        return medicines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if(nameFilter == null){
            nameFilter = new NameFilter();
        }
        return nameFilter;
    }

    private class NameFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length() > 0){
                ArrayList<Medicine> filterList = new ArrayList<Medicine>();
                for(int i = 0; i < mStringFilterList.size(); i++){
                    if((mStringFilterList.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            medicines = (ArrayList<Medicine>) results.values;
            notifyDataSetChanged();
        }
    }
}
