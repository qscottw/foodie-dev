package com.qw.mapper;

import com.qw.my.mapper.MyMapper;
import com.qw.pojo.Category;
import com.qw.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {
    public List<CategoryVO> getSubCatList(Integer rootCatId);
    public List getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}