package xion.newweather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 添加数据库，创建表provinces 和表cities
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREAT_TABLE_PROVINCE = "create table Province("+
            "id integer primary key autoincrement,"+
            "province_name text,"+
            "province_code text)";
    private static final String CREAT_TABLE_CITY = "create table City("+
            "id integer primary key autoincrement,"+
            "city_name text,"+
            "city_code text,"+
            "province_id integer)";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_PROVINCE);
        db.execSQL(CREAT_TABLE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
