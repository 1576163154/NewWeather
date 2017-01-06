package xion.newweather.HttpUrlConnection;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import xion.newweather.bean.City;
import xion.newweather.bean.Province;
import xion.newweather.database.WeatherDB;


/**
 * 用HttpUrlConnect 进行网络请求
 * 处理返回的信息
 */

public class HUCutil {
    /**
     * 对不同的url进行网络请求
     * @param address
     * @param listener
     */
    public static void sendHUCRequest(final String address, final HUCCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                try {
                    URL url = new URL(address);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(8000);
                    con.setReadTimeout(8000);
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null){
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 解析全国省市信息，并保存到数据库中
     * @param weatherDB
     * @param response
     * @return
     */
    public synchronized static boolean handleProvinceResponse(WeatherDB weatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");//先以“,”分为各个省份+代号
            if (allProvinces != null && allProvinces.length > 0){
                for (String p :
                        allProvinces) {
                    String[] arrayP = p.split("\\|");//再将各个省份按“|”分开 取得 name 和 code
                    Province province = new Province();
                    province.setProvinceName(arrayP[0]);
                    province.setProvinceName(arrayP[1]);
                    weatherDB.insertProvince(province);//存入数据库
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 处理某一省份的所有城市信息
     * @param weatherDB
     * @param response
     * @param provinceId
     * @return
     */
    public synchronized static boolean handleCityResponse(WeatherDB weatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0 ){
                for (String c :
                        allCities) {
                    String[] arrayC = c.split("\\|");
                    City city = new City();
                    city.setCityCode(arrayC[0]);
                    city.setCityName(arrayC[1]);
                    city.setProvinceId(provinceId);
                    weatherDB.insertCity(city);
                }
                return true;
            }
        }
        return false;
    }
}
