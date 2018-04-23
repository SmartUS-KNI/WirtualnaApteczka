package smartcity.kni.wirtualnaapteczka.layout.helpers;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Enumeration;
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
                (String[]) spinnerStrings.toArray(new String[spinnerStrings.size()]));
        spinner.setAdapter(spinnerAdapter);
    }
}
