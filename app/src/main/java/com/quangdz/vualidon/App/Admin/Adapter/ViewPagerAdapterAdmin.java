package com.quangdz.vualidon.App.Admin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quangdz.vualidon.App.Admin.Fragment.PhimFragment;
import com.quangdz.vualidon.App.Admin.Fragment.TaiKhoanFragment;
public class ViewPagerAdapterAdmin extends FragmentStatePagerAdapter {
    public ViewPagerAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhimFragment();
            case 1:
                return new TaiKhoanFragment();
            default:
                return new PhimFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
