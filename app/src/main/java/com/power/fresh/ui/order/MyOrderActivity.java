package com.power.fresh.ui.order;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.power.fresh.R;
import com.power.fresh.fragment.OrderFormFragment;
import com.power.fresh.fragment.ShoppingCartFragment;
import com.powerrich.common.base.UIActivity;

/**
 * 我的订单
 */
public class MyOrderActivity extends UIActivity {


    private FragmentTransaction mFragmentTransaction;
    private  Fragment currentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_cart;
    }


    public static void startActivity(Context context, int position,String title,int intentType) {
        Intent intent = new Intent(context, MyOrderActivity.class);
        intent.putExtra("TYPE", position);
        intent.putExtra("Title", title);
        intent.putExtra("IntentType", intentType);
        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        int position = getIntent().getIntExtra("TYPE", 0);
        int intentType = getIntent().getIntExtra("IntentType", -1);
        String title = getIntent().getStringExtra("Title");
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        OrderFormFragment shoppingCartFragment = OrderFormFragment.newInstance(intentType);
        shoppingCartFragment.isActivity(position,title);
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
