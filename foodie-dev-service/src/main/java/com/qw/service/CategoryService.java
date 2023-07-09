package com.qw.service;


import com.qw.pojo.Carousel;
import com.qw.pojo.Category;
import com.qw.pojo.vo.CategoryVO;
import com.qw.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {
    /**
     * find all level 1 categories
     * @return
     */
    public List<Category> queryAllRootCat();

    /**
     * find all children cats info by ID
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    //查询最新六个商品数据
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);



}
