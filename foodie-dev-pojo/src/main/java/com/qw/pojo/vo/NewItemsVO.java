package com.qw.pojo.vo;

import java.util.List;

//最新商品VO
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String bgColor;

    private List<SimpleItemsVO> simpleItemList;

    public Integer getRootCatId() {
        return rootCatId;
    }

    public void setRootCatId(Integer rootCatId) {
        this.rootCatId = rootCatId;
    }

    public String getRootCatName() {
        return rootCatName;
    }

    public void setRootCatName(String rootCatName) {
        this.rootCatName = rootCatName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public List<SimpleItemsVO> getSimpleItemList() {
        return simpleItemList;
    }

    public void setSimpleItemList(List<SimpleItemsVO> simpleItemList) {
        this.simpleItemList = simpleItemList;
    }
}
