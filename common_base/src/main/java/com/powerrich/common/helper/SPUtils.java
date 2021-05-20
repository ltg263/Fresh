package com.powerrich.common.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.SharedPreferencesCompat;

import com.google.gson.reflect.TypeToken;
import com.powerrich.common.base.BaseApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 文件名：  SharedPreferencesUtils
 * 文件描述：Android常用的工具类SharedPreferences封装类
 * 作者：    Wangzheng
 * 创建时间：2019/2/26
 * 版本号：  Ver1.0
 */
public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";
    public static final String LNG = "LNG";
    public static final String LAT = "LAT";
    public static final String CITYCODE = "CITYCODE";
    public static final String CITYENTITY = "CITYENTITY";


    private static Context context;

    public static void init(Application application) {
        context = application;
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    public static void put(String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Double) {
            editor.putLong(key, Double.doubleToRawLongBits((Double) object));
        } else {
            editor.putString(key, BaseApplication.getmGson().toJson(object));
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof Double) {
            if (sp.contains(key)) {
                return Double.longBitsToDouble(sp.getLong(key, 0));
            }
        }
        return defaultObject;
    }
    public static <T> T get(String key, Class<T> classOf) {
        try {
            SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            String jsonStr = sp.getString(key, null);
            return BaseApplication.getmGson().fromJson(jsonStr, classOf);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     */
    public static void clear() {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
