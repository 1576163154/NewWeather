package xion.newweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xion.newweather.HttpUrlConnection.HUCCallbackListener;
import xion.newweather.HttpUrlConnection.HUCutil;
import xion.newweather.bean.City;
import xion.newweather.bean.Province;
import xion.newweather.database.WeatherDB;
;

public class AddCityActivity extends AppCompatActivity {
    private LinearLayout ll_load;
    private Spinner spin_province;
    private Spinner spin_city;
    private List<Province> provinceList = new ArrayList<>();//用于存储获取到的省份信息
    private List<String> provinceStrList = new ArrayList<>();//用于适配的省份信息
    private List<City> cityList = new ArrayList<>();//用于存储获取到的城市信息
    private List<String> citStrList = new ArrayList<>();//用户适配的城市信息
    private Province selectedProvince;//当前选中的省份
    private String selectedCity;//当前用户确认添加的城市名
    private Button btn_addtolist;//添加城市按钮
    private Thread th_getCity;
    private boolean isGetCity = false;
    private WeatherDB weatherDB;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private String TYPE_PROVINCE = "province";
    private String TYPE_CITY = "city";
    private String PROVINCE_URL = "http://www.weather.com.cn/data/list3/city.xml";
    private String CITY_PREFIX_URL = "http://www.weather.com.cn/data/list3/city";
    private String CITY_SUFFIX_URL = ".xml";
    private String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcity);
        weatherDB = weatherDB.getInstance(this);//获取数据库对象
        initWidget();
        provinceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, provinceStrList);
        spin_province.setAdapter(provinceAdapter);
        cityAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,citStrList);
        spin_city.setAdapter(cityAdapter);
        initProvinceData();
        th_getCity = new Thread(){
            @Override
            public void run() {
                while (!isGetCity){
                    initCItyData();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        th_getCity.start();
        setWidget();
    }

    private void initWidget() {
        spin_province = (Spinner) findViewById(R.id.spin_province);
        spin_city = (Spinner) findViewById(R.id.spin_city);
        btn_addtolist = (Button) findViewById(R.id.btn_addtolist);
        ll_load = (LinearLayout) findViewById(R.id.ll_load);
    }

    private void setWidget() {
        spin_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) spin_province.getSelectedItem();// 获取spinner当前选定的值
                Log.i("Spin", value);
                Log.i("SpinPos", String.valueOf(position));
                Log.i("SpinId", String.valueOf(id));
                //获取当前选定的 省份 对象
                code = position+1 >= 10 ? String.valueOf (position + 1) : "0"+(position+1);
                if (provinceList.size() > 0){
                    Log.d("spin","点击事件中"+provinceList.size());
                    selectedProvince = new Province();
                    selectedProvince = provinceList.get(position);
                    Log.d("spin","点击事件中"+selectedProvince.getProvinceCode());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spin_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               //获取用户想要添加的城市名
                selectedCity = (String) spin_city.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (citStrList.size() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("selectedCity",selectedCity);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"还没选择城市呢",Toast.LENGTH_SHORT);
                }
            }
        });
    }

    /**
     * 第一次通过网络获取，之后优先从数据库获取
     */
    private void initProvinceData() {
        provinceList = weatherDB.selectProvince();
        Log.i("Province", String.valueOf(provinceList.size()));
        if (provinceList.size() > 0) {
            closeLoadInfo();
            provinceStrList.clear();//添加之前清空防止重复添加
            for (Province p :
                    provinceList) {
                provinceStrList.add(p.getProvinceName());
            }
            provinceAdapter.notifyDataSetChanged();//更新数据源
            spin_province.setSelection(0);//默认选中北京
        } else {
            //通过网络获取
            getFromInternet(null, TYPE_PROVINCE);
        }
    }

    /**
     *
     */
    private void initCItyData() {
        //在获取到省份的情况，进一步获取改省份的所有城市名进行适配
        //由于加载城市，是在 省份 之后，并且默认之后执行一次，不需要开启线程 不断进行获取
        if (selectedProvince != null) {
            cityList = weatherDB.selectCity(selectedProvince.getId());
            if (cityList.size() > 0) {
                citStrList.clear();
                for (City c :
                        cityList) {
                    citStrList.add(c.getCityName());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cityAdapter.notifyDataSetChanged();

                    }
                });
            }else {
                getFromInternet(code,TYPE_CITY);
            }
        }
    }

    /**
     * 从互联网获取省份或城市信息
     *
     * @param code 获取特定省份所有城市时的省份代码
     * @param type 获取类型（省份还是城市）
     */
    private void getFromInternet(String code, final String type) {
        String url;
        if (TextUtils.isEmpty(code)) {
            url = PROVINCE_URL;
        } else {
            url = CITY_PREFIX_URL + code + CITY_SUFFIX_URL;
        }
        showLoadInfo();
        HUCutil.sendHUCRequest(url, new HUCCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean isOk = false;//数据是否成功处理并存储到数据库标识位
                if (type.equals(TYPE_PROVINCE)) {
                    isOk = HUCutil.handleProvinceResponse(weatherDB, response);
                } else if (type.equals(TYPE_CITY)) {
                    //解析特定省份的所有城市信息
                    isOk = HUCutil.handleCityResponse(weatherDB,response,selectedProvince.getId());
                }
                if (isOk) {
                    //返回ui线程，修改界面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (type.equals(TYPE_PROVINCE)) {
                                //再次返回数据库去获取省份信息
                                initProvinceData();
                            }else if ((type.equals(TYPE_CITY))){
                                initCItyData();
                            }

                            closeLoadInfo();//最后关闭 加载提示
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeLoadInfo();
                        Toast.makeText(AddCityActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void showLoadInfo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ll_load.setVisibility(View.VISIBLE);
            }
        });

    }

    private void closeLoadInfo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ll_load.setVisibility(View.GONE);
            }
        });    }
}
