package xion.newweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xion.newweather.R;
import xion.newweather.bean.CityWeatherInfo;

/**
 * Created by Administrator on 2017/1/4.
 */

public class LeftFragment extends Fragment {
    private ListView lv_left;
    private String[] testStr = {"1","2","3","4"};
    private List<CityWeatherInfo.DataBean.ForecastBean> forecastBeanList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View leftView = LayoutInflater.from(getContext()).inflate(R.layout.frag_left,null);
        lv_left = (ListView) leftView.findViewById(R.id.lv_left);
//        adapter = new LeftListAdapter(forecastBeanList);
        lv_left.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,testStr));
//        初始化数据
//        模拟
        initData();

        return leftView;
    }

    private void initData() {
        forecastBeanList = new ArrayList<>();

    }


}
