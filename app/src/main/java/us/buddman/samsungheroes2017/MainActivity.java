package us.buddman.samsungheroes2017;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import us.buddman.samsungheroes2017.databinding.ActivityMainBinding;
import us.buddman.samsungheroes2017.fragment.DailyMissionFragment;
import us.buddman.samsungheroes2017.fragment.PIMFragment;
import us.buddman.samsungheroes2017.fragment.TopicFragment;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    TabLayout tabLayout;
    CoinPagerAdapter mainPagerAdapter;
    ViewPager mainPager;
    String[] title = new String[]{"Pay it forward", "오늘의 주제", "Daily Mission"};

    protected void setDefault() {
        disableToggle();
        binding = (ActivityMainBinding) baseBinding;
        mainPagerAdapter = new CoinPagerAdapter(getSupportFragmentManager());

        mainPager = binding.viewPager;
        mainPager.setAdapter(mainPagerAdapter);
        mainPager.setOffscreenPageLimit(3);
        tabLayout = binding.tabLayout;
        for (String s : title) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }
        tabLayout.setupWithViewPager(mainPager);
      mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.receivedMessage:
                startActivity(new Intent(getApplicationContext(), ReceivedMessageActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private class CoinPagerAdapter extends FragmentStatePagerAdapter {

        public CoinPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new PIMFragment();
                case 1:
                    return new TopicFragment();
                case 2:
                    return new DailyMissionFragment();
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

    }
}
