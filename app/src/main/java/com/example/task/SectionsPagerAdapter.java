package com.example.task;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    //private static final String[] TAB_TITLES = new String[]{"business-insider","CNN","BUZZFEED","ESPN","bbc-news","politico","fox-news","google-news","AL-JAZEERA-ENGLISH","news-com-au"};
    private final Context mContext;
    int total;
    List<people> object;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int total, List<people> obj) {
        super(fm);
        mContext = context;
        this.total = total;
        this.object = obj;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new homeacti(object);

            case 1:
                return new contactFragment();

            case 2:
                return new PlaceholderFragment();

            default:
                return null;
        }

    }



//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString();
//    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return total;
    }
}