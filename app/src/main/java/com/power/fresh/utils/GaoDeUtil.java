package com.power.fresh.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.powerrich.common.helper.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/06/16 09:38
 */
public class GaoDeUtil {
    //高德APP
    private PackageManager mPackageManager;
    private static List<String> mPackageNames = new ArrayList<>();
    private static final String GAODE_PACKAGE_NAME = "com.autonavi.minimap";
    private static final String BAIDU_PACKAGE_NAME = "com.baidu.BaiduMap";
/*private double currLocationX = 39.9076860000;
private double currLocationY = 116.4012450000;
private double locationX = 40.0836620000;
private double locationY = 116.4127900000;
private String storeName = "北京饭店";*/

    private double currLocationX;//起点 纬度
    private double currLocationY;//起点 经度



    private double locationX;//终点 纬度
    private double locationY;//终点 经度
    private String storeName;//地点名称


    private static FragmentActivity mFragmentActivity;


    public static final GaoDeUtil getInstance( FragmentActivity fragmentActivity) {
        mFragmentActivity=fragmentActivity;
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final GaoDeUtil INSTANCE = new GaoDeUtil();
    }

    private GaoDeUtil() {
        mPackageManager =mFragmentActivity.getPackageManager();
    }


    public  void  openMap(double locationLATX,double locationLNGY,String storeName){

        this.locationX = locationLATX;
        this.locationY = locationLNGY;
        this.storeName = storeName;
        this.currLocationY = (double) SPUtils.get(Constant.LNG,0.0D);
        this.currLocationX = (double) SPUtils.get(Constant.LAT,0.0D);

        if(haveGaodeMap()){//如果安装高德APP
            openGaodeMapToGuide();//打开高德APP
        } else {//否则 打开浏览器
            ToastUtils.show("高德地图未安装，为您打开浏览器");
            openBrowserToGuide();

        }
    }

    /**
     * 打开高德地图
     * https://lbs.amap.com/api/amap-mobile/guide/android/route
     */
    private void openGaodeMapToGuide() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//
//
//
//
//        String url;
//        Log.i("高德定位：","经度："+currLocationY+" ,纬度："+currLocationX);
//        if (currLocationX == 0.0 || currLocationY == 0.0){
//            url = "androidamap://route?sourceApplication=amap&dlat="+locationX+"&dlon="+locationY+"&dname="+storeName+"&dev=0&t=1";
//        }else {
//            url = "androidamap://route?sourceApplication=amap&slat="+currLocationX+"&slon="+currLocationY
//                    +"&dlat="+locationX+"&dlon="+locationY+"&dname="+storeName+"&dev=0&t=1";
//        }
//
//
//
//        Uri uri = Uri.parse(url);
//        //将功能Scheme以URI的方式传入data
//        intent.setData(uri);
//        //启动该页面即可
//        mFragmentActivity.startActivity(intent);



        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.autonavi.minimap");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse("androidamap://route?sourceApplication=" + R.string.app_name
                + "&sname=我的位置&dlat=" + locationX
                + "&dlon=" + locationY
                + "&dname=" + storeName
                + "&dev=0&m=0&t=3"));
        mFragmentActivity.startActivity(intent);


    }


    /**
     * 打开浏览器
     */
    private void openBrowserToGuide() {
        String url = "http://uri.amap.com/navigation?to=" + locationY + "," + locationX + "," +
                storeName + "&mode=car&policy=1&src=mypage&coordinate=gaode&callnative=0";
        Log.e("jsc", "GaoDeUtil-openBrowserToGuide:"+url);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mFragmentActivity.startActivity(intent);
    }





    public void initPackageManager(){
        List<PackageInfo> packageInfos = mPackageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                mPackageNames.add(packageInfos.get(i).packageName);
            }
        }
    }

    /**
     * 判断有无安装高德
     * @return
     */
    public boolean haveGaodeMap(){
        initPackageManager();
        return mPackageNames.contains(GAODE_PACKAGE_NAME);
    }

}
