package com.power.fresh.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.power.fresh.R;
import com.power.fresh.fragment.ShoppingCartFragment;
import com.powerrich.common.base.UIActivity;

/**
 * 购物车
 */
public class ShoppingCartActivity extends UIActivity {


    private FragmentTransaction mFragmentTransaction;
    private  Fragment currentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected void initView() {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        ShoppingCartFragment shoppingCartFragment = ShoppingCartFragment.newInstance();
        shoppingCartFragment.isActivity();
        mFragmentTransaction.add(R.id.fl_content, shoppingCartFragment)
                .commit();
    }

    @Override
    protected int getTitleId() {
        return -1;
    }


    @Override
    protected void initData() {

    }
}
