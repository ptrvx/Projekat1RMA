package rs.edu.raf.rma.pvukovic16_projekat1.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.fragment.Tab1;
import rs.edu.raf.rma.pvukovic16_projekat1.fragment.Tab2;
import rs.edu.raf.rma.pvukovic16_projekat1.fragment.Tab3;
import rs.edu.raf.rma.pvukovic16_projekat1.fragment.Tab4;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,  R.string.tab_text_3, R.string.tab_text_4};
    private static final int FRAGMENT_COUNT = 4;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return Tab1.newInstance();
            case 1:
                return Tab2.newInstance();
            case 2:
                return Tab3.newInstance();
            case 3:
                return Tab4.newInstance();
        }

        return Tab3.newInstance();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}