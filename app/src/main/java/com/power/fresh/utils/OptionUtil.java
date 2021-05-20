package com.power.fresh.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.utils.option.JsonBean;
import com.power.fresh.utils.option.OptionBean;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.internal.Utils;

/**
 * @author AlienChao
 * @date 2020/05/11 09:14
 */
public class OptionUtil {
    private Context mContext;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private static boolean isLoaded = false;

    public IValueListerner mValueListerner;

    public OptionUtil setValueListerner(IValueListerner valueListerner) {
        mValueListerner = valueListerner;
        return this;
    }

    public interface IValueListerner{
        void setValue(String value);
    }

    public static final OptionUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final OptionUtil INSTANCE = new OptionUtil();
    }

    private OptionUtil() {

    }


    /**
     * 显示选择框
     *
     */
    public  void showOption(Context context, final List<OptionBean> options1Items, OnOptionsSelectListener onOptionsSelectListener) {




        KeyboardUtils.hideSoftInput((Activity) context);
        OptionsPickerView pvOptions = PickerViewUtils.getOptionsPickerView(context, onOptionsSelectListener).build();//切换时是否还原，设置默认选中第一项。

        pvOptions.setPicker(options1Items);

//        int index = 0;
//        for (int i = 0; i < options1Items.size(); i++) {
//            if (options1Items.get(i).getName().equals(textView.getText().toString())) {
//                index = i;
//                break;
//            }
//        }
//
//        pvOptions.setSelectOptions(index);

        pvOptions.show();
    }




    public void showAdressView(Context mContext) {//解析数据
        this.mContext = mContext;
        if (thread == null) {//如果已创建就不再重新创建子线程了


            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 子线程中解析省市区数据
                    initJsonData();
                }
            });
            thread.start();
        } else {
            if (isLoaded == true
            ) {
                showPickerView();
            }
        }
    }


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                if (mValueListerner!=null) {
                    mValueListerner.setValue(tx);
                }

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:

                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    showPickerView();
                    break;

                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };


    private void initJsonData() {//解析数据


        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(mContext, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }



    /**
     * Hide the soft input.
     *
     * @param activity The activity.
     */
    public static void hideSoftInput(@NonNull final Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) {
            View decorView = activity.getWindow().getDecorView();
            View focusView = decorView.findViewWithTag("keyboardTagView");
            if (focusView == null) {
                view = new EditText(activity);
                view.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(view, 1, 1);
            } else {
                view = focusView;
            }
            view.requestFocus();
        }
        hideSoftInput(view,activity);
    }

    /**
     * Hide the soft input.
     *
     * @param view The view.
     */
    public static void hideSoftInput(@NonNull final View view,Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {return;}
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
