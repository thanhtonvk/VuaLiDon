package com.quangdz.vualidon.App.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.quangdz.vualidon.App.Fragment.FavouriteFragment;
import com.quangdz.vualidon.App.Fragment.HomeFragment;
import com.quangdz.vualidon.App.Fragment.SearchFragment;
import com.quangdz.vualidon.App.Fragment.UserFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new FavouriteFragment();
            case 3:
                return new UserFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
