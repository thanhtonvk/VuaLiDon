package com.quangdz.vualidon.App;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quangdz.vualidon.App.Adapter.ViewPagerAdapter;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.R;

public class MainAppActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        anhXa();
        Toast.makeText(MainAppActivity.this, "Xin chào "+ Common.taiKhoan.getHoten(), Toast.LENGTH_SHORT).show();
        setFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_favourite:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_profile:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void anhXa() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);
    }

    private void setFragment() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_search);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_favourite);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_profile);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.action_home);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.action_search);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.action_favourite);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.action_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}