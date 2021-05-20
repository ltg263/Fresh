package com.power.fresh.indicator.abs;


import com.power.fresh.indicator.mode.PositionData;

import java.util.List;

/**
 */
public interface IPagerIndicator {
    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);

    void onPositionDataProvide(List<PositionData> dataList);
}
