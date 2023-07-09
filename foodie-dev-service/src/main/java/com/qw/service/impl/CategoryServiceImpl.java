package com.qw.service.impl;


import com.qw.mapper.CarouselMapper;
import com.qw.mapper.CategoryMapper;
import com.qw.mapper.CategoryMapperCustom;
import com.qw.pojo.Carousel;
import com.qw.pojo.Category;
import com.qw.pojo.vo.CategoryVO;
import com.qw.pojo.vo.NewItemsVO;
import com.qw.service.AddressService;
import com.qw.service.CarouselService;
import com.qw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    ;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Category> queryAllRootCat() {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);

        List<Category> result = categoryMapper.selectByExample(example);
        return result;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);
        return categoryMapperCustom.getSixNewItemsLazy(map);
    }
}
