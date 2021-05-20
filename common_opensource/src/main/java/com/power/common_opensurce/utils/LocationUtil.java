package com.power.common_opensurce.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/20 15:11
 */
public class LocationUtil {

    private  ILocationListener mILocationListener;

    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;

    public static final LocationUtil getInstance() {
        return new LocationUtil();
    }

//    private static class SingletonHolder {
//        private static final LocationUtil INSTANCE = new LocationUtil();
//    }

    public interface ILocationListener{
        void getAMapLocation(AMapLocation amapLocation);
    }

    private void setILocationListener(ILocationListener ILocationListener) {
        mILocationListener = ILocationListener;
    }

    private LocationUtil() {

    }
    public AMapLocationClientOption mLocationOption = null;
    public void getLocation(FragmentActivity activity,ILocationListener iLocationListener) {

        CcPermissions.with(activity)
                .permission(PermissionName.ACCESS_FINE_LOCATION)
                .request(new Consumer() {
                    @Override
                    public void accept(List<String> granted, boolean isAll) {

                        setILocationListener(iLocationListener);
                        //初始化定位
                        mLocationClient = new AMapLocationClient(activity);

                        //设置定位回调监听
                        mLocationClient.setLocationListener(mAMapLocationListener);
                         //启动定位
                        mLocationClient.startLocation();

                    }
                });

    }

    //异步获取定位结果
    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation it) {
         LogUtils.i("定位", "LocationUtil-onLocationChanged:"+it.toString());
            if (it != null) {
                if (it.getErrorCode() == 0) {
                    if (mILocationListener!=null) {



                        if(!TextUtils.isEmpty(it.getAddress())){



                        SPUtils.put(SPUtils.LNG, it.getLongitude());
                        SPUtils.put(SPUtils.LAT, it.getLatitude());
                        SPUtils.put(SPUtils.CITYCODE, it.getCityCode());
                        LocationDataBean locationDataBean = new LocationDataBean();
                        locationDataBean.setLatitude(it.getLatitude());
                        locationDataBean.setLongitude(it.getLongitude());
                        locationDataBean.setProvider(it.getProvince());
                        locationDataBean.setCity(it.getCity());
                        locationDataBean.setDistrict(it.getDistrict());

                        locationDataBean.setAoiname(it.getAoiName());
                        locationDataBean.setPoiname(it.getPoiName());
                        locationDataBean.setStreetNum(it.getStreetNum());
                        locationDataBean.setRoad(it.getRoad());
                        locationDataBean.setStreet(it.getStreet());
                        locationDataBean.setCityCode(it.getCityCode());
                        locationDataBean.setAdCode(it.getAdCode());

                        SPUtils.put(SPUtils.CITYENTITY, App.getmGson().toJson(locationDataBean));

                        mILocationListener.getAMapLocation(it);
                        mLocationClient.stopLocation();
                        }


                    }
                  //  mLocationClient.setLocationListener(null);
                    mAMapLocationListener =null;
                }else{
                    ToastUtils.show("定位错误");
                }
            }else{
                ToastUtils.show("定位信息为空");
            }
            // TODO: 2021/1/25 可以做一个缓存在这儿





        }
    };


}
