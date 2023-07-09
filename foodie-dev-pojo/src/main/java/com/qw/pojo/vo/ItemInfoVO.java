package com.qw.pojo.vo;

import com.qw.pojo.Items;
import com.qw.pojo.ItemsImg;
import com.qw.pojo.ItemsParam;
import com.qw.pojo.ItemsSpec;

import java.util.List;

public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemsImgsList;
    private List<ItemsSpec> itemSpecList;
    private List<ItemsParam> itemParams;

    public List<ItemsParam> getItemParams() {
        return itemParams;
    }

    public void setItemParams(List<ItemsParam> itemParams) {
        this.itemParams = itemParams;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }

    public List<ItemsImg> getItemsImgsList() {
        return itemsImgsList;
    }

    public void setItemsImgsList(List<ItemsImg> itemsImgsList) {
        this.itemsImgsList = itemsImgsList;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }


}
