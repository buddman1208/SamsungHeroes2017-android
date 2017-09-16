package us.buddman.samsungheroes2017;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

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
        for(String s : title){
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }
        tabLayout.setupWithViewPager(mainPager);

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
