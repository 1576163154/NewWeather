package xion.newweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import xion.newweather.R;

/**
 * Created by Administrator on 2017/1/4.
 */

public class MidFragment extends Fragment {
    private TextView tv_current_city;
    private TextView tv_weather_desp;
    private TextView tv_type;
    private TextView tv_current_tem;
    private TextView tv_low;
    private TextView tv_high;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View midView = LayoutInflater.from(getContext()).inflate(R.layout.frag_mid,null);
        tv_current_city = (TextView) midView.findViewById(R.id.tv_current_city);
        tv_weather_desp = (TextView) midView.findViewById(R.id.tv_weather_desp);
        tv_type = (TextView) midView.findViewById(R.id.tv_type);
        tv_current_tem = (TextView) midView.findViewById(R.id.tv_current_tem);
        tv_low = (TextView) midView.findViewById(R.id.tv_low);
        tv_high = (TextView) midView.findViewById(R.id.tv_high);


        return midView;
    }

}
