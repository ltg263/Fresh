package com.power.fresh.utils.option;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/11 09:04
 */
public class CityBean {

    public List<City> data;
    public int status;

    public static class City implements IPickerViewData{

        public int id;
        public String letter;
        public String level;
        public String name;
        public String parentId;
        public String shortNameId;
        public ArrayList<City> children;

        @Override
        public String getPickerViewText() {
            return name;
        }
    }


}
