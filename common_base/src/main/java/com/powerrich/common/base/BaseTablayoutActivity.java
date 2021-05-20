package com.powerrich.common.base;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hjq.common.R;
import com.powerrich.common.base.adapter.BaseFragmentPagerAdapter;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class BaseTablayoutActivity<T> extends UIActivity implements BaseListFragmentConstructor.OnLoadDataListener {


    private TitleBar titleBar;
    private TabLayout tabLayout;
    protected ViewPager viewPager;
    protected int mIndex=0;

    protected ArrayList<BaseListFragmentConstructor> mFragmentList=new ArrayList<>();
    private BaseFragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.public_activity_base_tablayout;
    }

    @Override
    protected void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        titleBar.setTitle(setTitleText());

        for (int i = 0; i < setTabTitleList().size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(setTabTitleList().get(i)));
            mFragmentList.add( BaseListFragmentConstructor.newInstance(getAdapter(),this,String.valueOf(i)));
        }

        mAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), setTabTitleList(), mFragmentList);
        //给ViewPager设置适配器
        viewPager.setAdapter(mAdapter);
        //将TabLayout与Viewpager联动起来
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabIndicatorFullWidth(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void reFreshData(){
       mAdapter.getCurrentFragment().refreshData();
    }


    @Override
    protected void initData() {
        viewPager.setCurrentItem(mIndex);

    }

    protected abstract String setTitleText();
    protected abstract ArrayList<String> setTabTitleList();
    protected abstract CommonRvAdapter<T> getAdapter();

    protected void notifyDataChanged(String type, List<T> data) {
        ( mFragmentList.get(Integer.decode(type))).notifyDataChanged(data);


    }

}
