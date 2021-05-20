package com.power.fresh.utils.dialog;

import android.view.Gravity;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.adapter.HomeDialogAdapter;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseDialogFragment;
import com.powerrich.common.dialog.ACopyDialog;
import com.powerrich.common.helper.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AlienChao
 * @date 2020/04/18 20:
 */
public class HomeDialog {

    private static List<HomeGouponBean.ListBean> beanList1;

    public static final class Builder
            extends BaseDialogFragment.Builder<ACopyDialog.Builder> {


        private HomeGouponBean.ListBean item;
        private RecyclerView mRecyclerView;
        private HomeDialogAdapter mHomeDialogAdapter;


        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_home);
            setAnimStyle(BaseDialog.AnimStyle.SCALE);
            //setGravity(Gravity.BOTTOM);
            setGravity(Gravity.CENTER);

            mRecyclerView = findViewById(R.id.mRecyclerView);
            mHomeDialogAdapter = new HomeDialogAdapter(null);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setAdapter(mHomeDialogAdapter);

            findViewById(R.id.iv_dialog_close).setOnClickListener(v -> dismiss());


        }

        public HomeDialog.Builder setListBean(List<HomeGouponBean.ListBean> beanList){

            beanList1 = beanList;
            this.item=beanList.get(0);
            if (item==null) {
                return this;
            }
            mHomeDialogAdapter.setNewData(beanList);

            findViewById(R.id.tv_lingqu).setOnClickListener(v ->{
                SPUtils.put(Constant.NEWHAND_FLAG, false);
                ArrayList<Integer> integers = new ArrayList<>();
                for (int i = 0; i < beanList1.size(); i++) {
                    integers.add(beanList1.get(i).getCouponId());
                }

                Constant.get().userCouponSave(integers, new Constant.IListener<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        ToastUtils.show("领取成功");
                        dismiss();
                    }
                });
            } );
            return this;
        }
    }
}
