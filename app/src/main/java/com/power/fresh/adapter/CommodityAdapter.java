package com.power.fresh.adapter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.fresh.R;
import com.power.fresh.bean.SeckillBean;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/27 14:24
 */
public abstract class CommodityAdapter extends CommonRvAdapter<SeckillBean> {

    private Context mContext;

    private Context getActivity() {
        return mContext;
    }

    public CommodityAdapter(Context context, List<SeckillBean> mData) {
        super(context, mData, R.layout.item_search);
        mContext = context;
    }

    @Override
    public void convert(CommonViewHolderRv holder, SeckillBean item, int position) {
        RecyclerView rvSearchChird = holder.itemView.findViewById(R.id.rv_search_chird);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvSearchChird.setLayoutManager(manager);


        List<SeckillBean> booths2 = new ArrayList<>();
        booths2.add(new SeckillBean());
        booths2.add(new SeckillBean());
        booths2.add(new SeckillBean());

        CommonRvAdapter mAdapter = new CommonRvAdapter<SeckillBean>(getActivity(), booths2, R.layout.item_search_chird) {
            @Override
            public void convert(CommonViewHolderRv holder, SeckillBean booth, int position) {

            }
        };

        rvSearchChird.setNestedScrollingEnabled(false);
        rvSearchChird.setAdapter(mAdapter);

    }
}
