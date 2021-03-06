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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.utils.option.CityBean;
import com.power.fresh.utils.option.OptionBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author AlienChao
 * @date 2020/05/11 09:14
 */
public class OptionUtil1 {
    private Context mContext;

//    private List<JsonBean> options1Items = new ArrayList<>();
//    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
//    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private List<CityBean.City> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<CityBean.City>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<CityBean.City>>> options3Items = new ArrayList<>();

    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private static boolean isLoaded = false;

    public IValueListerner mValueListerner;
    public CityListerner mCityListerner;

    public OptionUtil1 setValueListerner(IValueListerner valueListerner) {
        mValueListerner = valueListerner;
        return this;
    }
    public OptionUtil1 setCityListerner(CityListerner cityListerner) {
        mCityListerner = cityListerner;
        return this;
    }

    public interface IValueListerner{
        void setValue(String value);
    }

    public interface CityListerner{
        //?????????
        void getDate(CityBean.City a, CityBean.City b, CityBean.City c);
    }

    public static final OptionUtil1 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final OptionUtil1 INSTANCE = new OptionUtil1();
    }

    private OptionUtil1() {

    }


    /**
     * ???????????????
     *
     */
    public  void showOption(Context context, final List<OptionBean> options1Items, OnOptionsSelectListener onOptionsSelectListener) {




        KeyboardUtils.hideSoftInput((Activity) context);
        OptionsPickerView pvOptions = PickerViewUtils.getOptionsPickerView(context, onOptionsSelectListener).build();//??????????????????????????????????????????????????????

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




    public void showAdressView(Context mContext) {//????????????
        this.mContext = mContext;
        if (thread == null) {//????????????????????????????????????????????????
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // ?????????????????????????????????
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


    private void showPickerView() {// ???????????????

        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //?????????????????????????????????????????????
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2).name : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3).name : "";

                String tx = opt1tx + opt2tx + opt3tx;
                if(mCityListerner != null){
                    mCityListerner.getDate(options1Items.get(options1),options2Items.get(options1).get(options2)
                    ,options3Items.get(options1).get(options2).get(options3));
                }
//                if (mValueListerner!=null) {
//                    mValueListerner.setValue(tx);

//                }

            }
        })


                .setTitleText("????????????")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //???????????????????????????
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//???????????????
        pvOptions.setPicker(options1Items, options2Items);//???????????????*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//???????????????
       // pvOptions.setSelectOptions(3,5,4);
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


    private void initJsonData() {//????????????


        /**
         * ?????????assets ????????????Json??????????????????????????????????????????????????????
         * ???????????????????????????
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(mContext, "province1.json");//??????assets????????????json????????????

        ArrayList<CityBean.City> jsonBean = parseData(JsonData);//???Gson ????????????

        /**
         * ??????????????????
         *
         * ???????????????????????????JavaBean????????????????????????????????? IPickerViewData ?????????
         * PickerView?????????getPickerViewText????????????????????????????????????
         */
        options1Items = jsonBean;


        for (int i = 0; i < jsonBean.size(); i++) {//????????????
            CityBean.City city = jsonBean.get(i);
            ArrayList<ArrayList<CityBean.City>> al2 = new ArrayList<>();
            if(city.children != null){
                options2Items.add(city.children);
//                options3Items.add(options2Items);
                for(int j = 0; j< city.children.size();j++){
                    if(city.children.get(j).children != null){
                        al2.add(city.children.get(j).children);
                    }else{
                        al2.add(new ArrayList<>());
                    }
                }
            }else{
                options2Items.add(new ArrayList<>());
            }
            options3Items.add(al2);
        }
        System.out.println(options3Items);
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<CityBean.City> parseData(String result) {//Gson ??????
        ArrayList<CityBean.City> detail = new ArrayList<>();
        try {
            JSONObject dataObj = new JSONObject(result);
            JSONArray data  = dataObj.getJSONArray("data");
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityBean.City entity = gson.fromJson(data.optJSONObject(i).toString(), CityBean.City.class);
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
