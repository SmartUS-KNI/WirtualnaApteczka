package smartcity.kni.wirtualnaapteczka.Popups;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import smartcity.kni.wirtualnaapteczka.ActivityMedicineList;
import smartcity.kni.wirtualnaapteczka.R;
import smartcity.kni.wirtualnaapteczka.enums.ESort;

/**
 * Created by KozMeeN on 31/05/2018.
 */

public class PopupSort extends Activity {

    public static int POPUP_SORT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_sort);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

        Button alphabeticSort = (Button) findViewById(R.id.aplhabeticSort);
        alphabeticSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", ESort.ALPHABETIC);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }
}
