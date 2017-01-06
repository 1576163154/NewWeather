package xion.newweather.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xion.newweather.AddCityActivity;
import xion.newweather.MainActivity;
import xion.newweather.R;
import xion.newweather.bean.CityWeatherInfo;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator on 2017/1/4.
 */

public class RightFragment extends Fragment {
    private String addCityName;
    private ListView lv_right;
    private Button btn_right_add;
    private List<CityWeatherInfo> cityWeatherInfoList;
    private int GET_CITY = 1;
    private String[] testStr = {"1","2","3","4"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rightView = LayoutInflater.from(getContext()).inflate(R.layout.frag_right, null);
        lv_right = (ListView) rightView.findViewById(R.id.lv_right);
        btn_right_add = (Button) rightView.findViewById(R.id.btn_right_addcity);
        lv_right.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,testStr));
        //初始化数据这里直接从网络获取
        initData();
        btn_right_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCityActivity.class);

                startActivityForResult(intent, GET_CITY);
            }
        });
        return rightView;
    }

    private void initData() {
        cityWeatherInfoList  = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CityWeatherInfo.DataBean dataBean = new CityWeatherInfo.DataBean();
            dataBean.setCity("北京");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //获取从AddCityActivity传过来的 城市名
                    addCityName = data.getStringExtra("selectedCity");
                    Log.i("addCityName","选中的城市名:"+addCityName);
                }
                break;
        }
    }
}
