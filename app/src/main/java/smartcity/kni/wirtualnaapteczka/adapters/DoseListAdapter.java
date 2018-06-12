package smartcity.kni.wirtualnaapteczka.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import smartcity.kni.wirtualnaapteczka.Dose;
import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.enums.ERegularDoseType;

public class DoseListAdapter extends ArrayAdapter<Dose> implements Filterable {

    //NameFilter nameFilter;
    private List<Dose> doses;
    private List<Dose> mStringFilterList;

    public DoseListAdapter(Activity context, List<Dose> doses) {
        super(context, 0, doses);
        mStringFilterList = doses;
        this.doses = doses;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dose_list_view_item, null);
        }

        Dose dose = getItem(position);

        TextView FirstTV = (TextView) listItemView.findViewById(R.id.dose_type_text_view);
        FirstTV.setText(dose.getTime().toString());

        TextView SecondTV = (TextView) listItemView.findViewById(R.id.dose_tag_text_view);
        SecondTV.setText(dose.getCount().toString());

        return listItemView;

    }

    @Override
    public int getCount() {
        return doses.size();
    }

    @Nullable
    @Override
    public Dose getItem(int position) {
        return doses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @NonNull
//    @Override
//    public Filter getFilter() {
//        if (nameFilter == null) {
//            nameFilter = new NameFilter();
//        }
//        return nameFilter;
//    }
//
//    private class NameFilter extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults();
//
//            if (constraint != null && constraint.length() > 0) {
//                ArrayList<Dose> filterList = new ArrayList<>();
//                for (int i = 0; i < mStringFilterList.size(); i++) {
//                    if ((mStringFilterList.get(i).getName().toUpperCase()).contains(constraint.toString().toUpperCase())) {
//                        filterList.add(mStringFilterList.get(i));
//                    }
//                }
//                results.count = filterList.size();
//                results.values = filterList;
//            } else {
//                results.count = mStringFilterList.size();
//                results.values = mStringFilterList;
//            }
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            doses = (ArrayList<Dose>) results.values;
//            notifyDataSetChanged();
//        }
//    }
}

