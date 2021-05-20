package com.powerrich.common.base.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.powerrich.common.base.BaseListFragmentConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/25 11:11
 */
public class BaseFragmentPagerAdapter<F extends BaseListFragmentConstructor> extends FragmentPagerAdapter {


    private ArrayList<String> tab_title_list;//存放标签页标题
    private ArrayList<F> fragment_list=new ArrayList<>();//存放ViewPager下的Fragment

    private F mCurrentFragment; // 当前显示的Fragment

    public BaseFragmentPagerAdapter(FragmentManager fm,ArrayList<String> tab_title_list, ArrayList<F> fragment_list) {
        super(fm);
        this.tab_title_list = tab_title_list;
        this.fragment_list = fragment_list;
    }


    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            // 记录当前的Fragment对象
            mCurrentFragment = (F) object;
        }
        super.setPrimaryItem(container, position, object);
    }


    @Override
    public F getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title_list.get(position);
    }


    public void addFragment(F fragment) {
        fragment_list.add(fragment);
    }

    /**
     * 获取Fragment集合
     */
    public List<F> getAllFragment() {
        return fragment_list;
    }

    /**
     * 获取当前的Fragment
     */
    public F getCurrentFragment() {
        return mCurrentFragment;
    }
}
