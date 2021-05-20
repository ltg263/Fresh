package com.power.fresh.utils.option;


import com.contrarywind.interfaces.IPickerViewData;


public class OptionBean implements IPickerViewData {


   private String name;
   private String tag;
   /** 筛选 */
   private String screening;






    public String getScreening() {
        return screening == null ? "" : screening;
    }

    public OptionBean setScreening(String screening) {
        this.screening = screening;
        return  this;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public OptionBean setName(String name) {
        this.name = name;
        return  this;
    }

    public OptionBean setTag(String tag) {
        this.tag = tag;
        return  this;
    }

    public String getTag() {
        return tag == null ? "" : tag;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }




}
