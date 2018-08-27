package smartcity.kni.wirtualnaapteczka;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import smartcity.kni.wirtualnaapteczka.adapters.MedicineFragmentPagerAdapter;

public class MedicineFormActivity extends AppCompatActivity {

    static Activity context;

    ViewPager medicineFormViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_medicine_form);

        setTitle(getResources().getString(R.string.add_medicine));

        context = MedicineFormActivity.this;

        medicineFormViewPager = (ViewPager) findViewById(R.id.medicine_form_view_pager);
        MedicineFragmentPagerAdapter medicineFragmentPagerAdapter = new MedicineFragmentPagerAdapter(getSupportFragmentManager());
        medicineFormViewPager.setAdapter(medicineFragmentPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_medicine_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public static Activity getContext() {
        return context;
    }
}
