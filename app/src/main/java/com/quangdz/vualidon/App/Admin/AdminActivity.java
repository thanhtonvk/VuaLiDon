package com.quangdz.vualidon.App.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quangdz.vualidon.App.Adapter.ViewPagerAdapter;
import com.quangdz.vualidon.App.Admin.Adapter.ViewPagerAdapterAdmin;
import com.quangdz.vualidon.App.MainAppActivity;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.R;

public class AdminActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        anhXa();
        setFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_phim:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_taikhoan:
                        viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });
    }

    private void anhXa() {
        bottomNavigationView = findViewById(R.id.bottom_nav_admin);
        viewPager = findViewById(R.id.view_pager_admin);
    }

    private void setFragment() {
        ViewPagerAdapterAdmin viewPagerAdapterAdmin = new ViewPagerAdapterAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapterAdmin);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_phim);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_taikhoan);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}