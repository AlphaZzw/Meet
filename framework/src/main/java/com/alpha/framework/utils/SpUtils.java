package com.alpha.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alpha.framework.BuildConfig;

public class SpUtils {

    /**
     * key - values 存储方式
     * 它的存储路径：data/data/packageName/shared_prefs/sp_name.xml
     * <p>
     * File存储：/sdcard/ 读写方式不一样
     * 数据库：LitePal
     * get/post:数据的读写
     */
    private volatile static SpUtils mInstance = null;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;

    private SpUtils() {
    }

    public static SpUtils getInstance() {
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SpUtils();
                }
            }
        }
        return mInstance;
    }

    public void initSp(Context mContext) {
        /**
         * MODE_PRIVATE：只限于本应用读写
         * MODE_WORLD_READABLE:支持其他应用读，但是不能写(已弃用)
         * MODE_WORLD_WRITEABLE:其他应用可以写(已弃用)
         */
        mSp = mContext.getSharedPreferences(BuildConfig.SP_NAME, Context.MODE_PRIVATE);
        mEditor = mSp.edit();
    }

    public void putInt(String key, int values) {
        mEditor.putInt(key, values);
        mEditor.commit();
    }

    public int getInt(String key, int defValues) {
        return mSp.getInt(key, defValues);
    }

    public void putString(String key, String values) {
        mEditor.putString(key, values);
        mEditor.commit();
    }

    public String getString(String key, String defValues) {
        return mSp.getString(key, defValues);
    }

    public void putBoolean(String key, boolean values) {
        mEditor.putBoolean(key, values);
        mEditor.commit();
    }

    public boolean getBoolean(String key, boolean defValues) {
        return mSp.getBoolean(key, defValues);
    }

    public void deleteKey(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }
}
