package xion.newweather.database;

/*
*往数据库相应表存取 省份，城市的信息
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import xion.newweather.bean.City;
import xion.newweather.bean.Province;


public class WeatherDB {
    private static final String DB_NAME = "xxjiang_db";
    private static final int DB_VERSION = 1;
    private static WeatherDB weatherDB;
    private SQLiteDatabase sqLiteDb;

    //通过构造方法完成数据库的创建
    public WeatherDB(Context context) {
        DBHelper dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        sqLiteDb = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context) {
        if (weatherDB == null) {
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * 往数据库 Province 插入 省份信息
     * @param province
     */
    public void insertProvince(Province province){
        if (province != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("province_name",province.getProvinceName());
            contentValues.put("province_code",province.getProvinceCode());
            sqLiteDb.insert("Province",null,contentValues);
        }
    }

    /**
     * 从数据库 province 表 获取所有省份
     * @return 存储provinceList
     */
    public List<Province> selectProvince(){
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = sqLiteDb.query("Province",null,null,null,null,null,null);
        //每次读取一行
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("province_name"));
                String code = cursor.getString(cursor.getColumnIndex("province_code"));
                Province province = new Province();
                province.setId(id);
                province.setProvinceName(name);
                province.setProvinceCode(code);
                provinceList.add(province);
            }while (cursor.moveToNext());
        }
        return provinceList;
    }

    /**
     * 往数据库 City表 中插入城市信息
     * @param city 城市
     */
    public void insertCity(City city){
        if (city != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_name",city.getCityName());
            contentValues.put("city_code",city.getCityCode());
            contentValues.put("province_id",city.getProvinceId());
            sqLiteDb.insert("City",null,contentValues);
        }
    }

    /**
     * 获取特定省份的所有城市
     * @param provinceId
     * @return
     */
    public List<City> selectCity(int provinceId){
        List<City> cityList = new ArrayList<>();
        Cursor cursor = sqLiteDb.query("City",null,"province_id = ?",
                new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            }while (cursor.moveToNext());
        }
        return cityList;
    }
}
