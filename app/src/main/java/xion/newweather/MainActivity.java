package xion.newweather;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import xion.newweather.adapter.FragPagerAdapter;
import xion.newweather.fragment.LeftFragment;
import xion.newweather.fragment.MidFragment;
import xion.newweather.fragment.RightFragment;


public class MainActivity extends AppCompatActivity {
    private Toolbar tb;
    private ViewPager vp;
    private TabLayout tl;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(tb);
        initWidget();
        setWidget();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initWidget() {
        vp = (ViewPager) findViewById(R.id.vp_test);
        tl = (TabLayout) findViewById(R.id.tl);

    }
    private void setWidget(){
        fragments = new ArrayList<>();
        LeftFragment leftFrag = new LeftFragment();
        MidFragment midFrag = new MidFragment();
        RightFragment rightFrag = new RightFragment();
        fragments.add(leftFrag);
        fragments.add(midFrag);
        fragments.add(rightFrag);
        vp.setAdapter(new FragPagerAdapter(getSupportFragmentManager(),fragments));
        tl.setupWithViewPager(vp);
        vp.setCurrentItem(1);
        tl.setTabMode(TabLayout.MODE_FIXED);
    }

}
