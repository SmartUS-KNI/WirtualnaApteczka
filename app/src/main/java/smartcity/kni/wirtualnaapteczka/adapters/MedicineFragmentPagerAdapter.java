package smartcity.kni.wirtualnaapteczka.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smartcity.kni.wirtualnaapteczka.fragments.DosesFragment;
import smartcity.kni.wirtualnaapteczka.fragments.MedicineBaseInfoFragment;
import smartcity.kni.wirtualnaapteczka.fragments.PacksFragment;

/**
 * Created by Aleksander on 26.08.2018.
 */

public class MedicineFragmentPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    private static String[] titles = {"Lek", "Opakowania", "Dawki"};

    public MedicineFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MedicineBaseInfoFragment();
            case 1:
                return new PacksFragment();
            case 2:
                return new DosesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
