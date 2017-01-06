package xion.newweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 不管是继承FragmentPagerAdapter还是
 * FragmentStatePagerAdapter都是用来显示viewPager包含多个fragment
 *后者，当frament离开当前页面时，会被释放节约资源
 * Created by Administrator on 2016/12/31.
 */

public class FragPagerAdapter extends FragmentStatePagerAdapter {
    private String[] group = new String[]{"预报","天气","城市"};
    private ArrayList<Fragment> fragments;

    public FragPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return group[position];
    }
}
