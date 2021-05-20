package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.power.fresh.R;
import com.power.fresh.widget.HackyViewPager;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ImgPreviewActivity extends UIActivity {



    @BindView(R.id.viewPager)
    HackyViewPager mViewPager;
    private List<String> mImgList;
    boolean isNet;
    private int position;


    public static void startActivity(Context context, ArrayList<String> imgList,int position) {
        Intent intent = new Intent(context, ImgPreviewActivity.class);
        intent.putStringArrayListExtra("imgList", imgList);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_img_preview;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setTitle("图片预览");

       // MyStatusBarUtil.setStatusBarColor(this, R.color.public_white);
        mImgList = new ArrayList<>();
        mImgList = getIntent().getStringArrayListExtra("imgList");
        position = getIntent().getIntExtra("position",0);
        isNet = getIntent().getBooleanExtra("isNet",true);
        MyAdapter adapter = new MyAdapter(this, mImgList,isNet);
        mViewPager.setAdapter(adapter);

        mViewPager.setCurrentItem(position);
    }

    public class MyAdapter extends PagerAdapter {
        private Context mContext;
        private List<String> mImgList;
        private boolean isNet;

        public MyAdapter(Context context, List<String> imgList,boolean isNet) {
            this.mContext = context;
            this.mImgList = imgList;
            this.isNet = isNet;
        }

        @Override
        public int getCount() {
            return mImgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if (isNet) {
                GlideLoad.loadImage(photoView,mImgList.get(position));
               // ImageLoad.setUrl(mContext, mImgList.get(position), photoView, true);
            }else {
                GlideLoad.loadImage(photoView,mImgList.get(position));
                //ImageLoad.setImg(mContext, mImgList.get(position), photoView,true);
            }
            photoView.getAttacher().update();
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}

