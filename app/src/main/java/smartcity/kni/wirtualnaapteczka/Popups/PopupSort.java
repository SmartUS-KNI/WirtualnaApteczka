package smartcity.kni.wirtualnaapteczka.Popups;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import smartcity.kni.wirtualnaapteczka.ActivityMedicineList;
import smartcity.kni.wirtualnaapteczka.R;

/**
 * Created by KozMeeN on 31/05/2018.
 */

public class PopupSort extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_sort);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));


    }
}
