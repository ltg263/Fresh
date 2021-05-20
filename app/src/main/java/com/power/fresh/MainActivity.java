package com.power.fresh;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.power.common_opensurce.App;
import com.power.common_opensurce.utils.LocationUtil;
import com.power.common_opensurce.utils.LocationDataBean;
import com.power.fresh.fragment.HomeFragment;
import com.power.fresh.fragment.OrderFormFragment;
import com.power.fresh.fragment.ShoppingCartFragment;
import com.power.fresh.fragment.SortFragment;
import com.power.fresh.fragment.type.DefaultUserFragment;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.adapter.BaseFragmentAdapter;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.widget.NoScrollViewPager;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends UIActivity implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.vp_home_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;
    private BaseFragmentAdapter<MyLazyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        if (JPushInterface.isPushStopped(getApplicationContext())) {
            JPushInterface.resumePush(this);
        }

        mViewPager.addOnPageChangeListener(this);

        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {


        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(SortFragment.newInstance());
        mPagerAdapter.addFragment(ShoppingCartFragment.newInstance());
        mPagerAdapter.addFragment(OrderFormFragment.newInstance(1));
        mPagerAdapter.addFragment(DefaultUserFragment.newInstance());

        mViewPager.setAdapter(mPagerAdapter);

        // 限制页面数量
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLocation();
        if (Constant.MainIsFirst) {
            mViewPager.setCurrentItem(0);
            Constant.MainIsFirst = false;
        }

    }

    private void initLocation() {
        LocationUtil.getInstance().getLocation(getActivity(), it -> {
            Log.e("jsc", "定位-MainActivity-initLocation:");
            SPUtils.put(Constant.LNG, it.getLongitude());
            SPUtils.put(Constant.LAT, it.getLatitude());
            SPUtils.put(Constant.CITYCODE, it.getCityCode());
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

            SPUtils.put(Constant.CITYENTITY, App.getmGson().toJson(locationDataBean));

        });
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;

            case 1:
                mBottomNavigationView.setSelectedItemId(R.id.menu_sort);
                break;
            case 2:
                mBottomNavigationView.setSelectedItemId(R.id.menu_shopping_car);
                break;
            case 3:
                mBottomNavigationView.setSelectedItemId(R.id.menu_orderform);
                break;
            case 4:
                mBottomNavigationView.setSelectedItemId(R.id.menu_me);
                break;
        }
    }

    public  void setCurrentViewPager(int position){
        mViewPager.setCurrentItem(position, false);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int i = menuItem.getItemId();
        if (i == R.id.menu_home) {// mViewPager.setCurrentItem(0);
            mViewPager.setCurrentItem(0, false);
            return true;
        } else if (i == R.id.menu_sort) {
            mViewPager.setCurrentItem(1, false);
            return true;
        } else if (i == R.id.menu_shopping_car) {
            mViewPager.setCurrentItem(2, false);
            return true;
        } else if (i == R.id.menu_orderform) {
            mViewPager.setCurrentItem(3, false);
            return true;
        } else if (i == R.id.menu_me) {
            mViewPager.setCurrentItem(4, false);
            return true;
        }
        return false;
    }
}
