package smartcity.kni.wirtualnaapteczka.layout.helpers;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.R;

/**
 * Created by Aleksander on 26.03.2018.
 */

public class SpinnerHelper {

    public static void fillSpinnerWithStrings(Spinner spinner, String prefix, List<String> strings) {
        List<String> spinnerStrings = new ArrayList<>();

        spinnerStrings.add(prefix);

        if (strings != null)
            spinnerStrings.addAll(strings);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(spinner.getContext(), R.layout.support_simple_spinner_dropdown_item,
                spinnerStrings.toArray(new String[spinnerStrings.size()]));
        spinner.setAdapter(spinnerAdapter);
    }

    public static void fillSpinnerWithStrings(Spinner spinner, String prefix, List<String> strings, boolean sort) {
        if (sort) {
            Collections.sort(strings);
        }

        fillSpinnerWithStrings(spinner, prefix, strings);
    }

    public static void select(Spinner spinner, String caption) {
        SpinnerAdapter adapter = spinner.getAdapter();
        String itemCaption = null;
        for (int i=0; i < adapter.getCount(); i++) {
            itemCaption = (String) adapter.getItem(i);

            if (itemCaption.equals(caption)) {
                spinner.setSelection(i);
                break;
            }

        }
    }
}
